package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.CardEntity;
import YU.PinforYouAPIServer.Entity.UserCardEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Other.Percent;
import YU.PinforYouAPIServer.Other.QRCode;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
import YU.PinforYouAPIServer.Service.UserCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

@RestController
public class UserCardController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired CardRepository cardRepository;
    @Autowired UserCardService userCardService;
    @Autowired UserCardRepository userCardRepository;

    @Getter @Setter
    static class outJsonFormat_show {
        private Integer card_id;
        private String card_name;
        private String card_num;
    }

    @Getter @Setter
    static class outJsonFormat_userCardInfo {
        private Integer user_id;
        private Integer card_id;
        private String card_name;
        private String card_last_num;
        private Integer discount_percentage;
        private String card_color;
    }

    @Getter @Setter
    static class outJsonFormat_QR {
        private Integer user_id;
        private Integer card_id;
    }

    @GetMapping("/userCard")
    @ResponseBody
    public ResponseEntity<String> showUserCard(@RequestParam("user_id") Integer user_id) throws JsonProcessingException {
        List<UserCardEntity> userCards = userCardRepository.findByUserId(user_id);
        List<outJsonFormat_show> jsonFormats = new ArrayList<>();

        for(UserCardEntity userCard : userCards) {
            outJsonFormat_show jsonFormat = new outJsonFormat_show();
            jsonFormat.card_id = userCard.getCard_id();
            jsonFormat.card_name = cardRepository.get(userCard.getCard_id()).getCard_name();
            jsonFormat.card_num = userCard.getCard_num();

            jsonFormats.add(jsonFormat);
        }

        Map<String, List<outJsonFormat_show>> jsonFormats1 = new HashMap<>();
        jsonFormats1.put("userCard_list", jsonFormats);

        String outputJson = mapper.writeValueAsString(jsonFormats1);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @GetMapping("/userCard/payRecommend")
    @ResponseBody
    public ResponseEntity<String> userCardPayRecommend(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("store_name") String store_name,
            @RequestParam("category") String category
    ) throws JsonProcessingException {
        Percent percent = new Percent();
        percent.reset();

        List<UserCardEntity> user_cards = userCardService.card_recommend_by_store(user_id, category, store_name);

        outJsonFormat_userCardInfo outJsonFormat;
        ArrayList<outJsonFormat_userCardInfo> outJsonFormats = new ArrayList<>();
        for(UserCardEntity user_card : user_cards) {
            outJsonFormat = new outJsonFormat_userCardInfo();

            outJsonFormat.user_id = user_card.getUser_id();
            outJsonFormat.card_id = user_card.getCard_id();
            outJsonFormat.card_name = cardRepository.get(outJsonFormat.getCard_id()).getCard_name();
            outJsonFormat.card_last_num = user_card.getCard_num().substring(15); // 맨 뒤 4자리
            outJsonFormat.discount_percentage = 100 - (int)(percent.set_card(user_card.getCard_id()) * 100);
            outJsonFormat.card_color = cardRepository.get(outJsonFormat.getCard_id()).getCard_color();

            outJsonFormats.add(outJsonFormat);
        }
        Map<String, ArrayList<outJsonFormat_userCardInfo>> outJsonFormats1 = new HashMap<>();
        outJsonFormats1.put("card_list", outJsonFormats);

        String outputJson = mapper.writeValueAsString(outJsonFormats1);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @GetMapping("/userCard/pay")
    @ResponseBody
    public ResponseEntity<ResponseEntity<byte[]>> userCardPay(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("card_id") Integer card_id
    ) throws IOException, WriterException {
        outJsonFormat_QR jsonFormat = new outJsonFormat_QR();
        jsonFormat.setUser_id(user_id);
        jsonFormat.setCard_id(card_id);
        String json = mapper.writeValueAsString(jsonFormat);

        QRCode qrCode = new QRCode();
        return new ResponseEntity<>(qrCode.generate(json), HttpStatus.OK);
    }
}
