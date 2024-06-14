package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.CardEntity;
import YU.PinforYouAPIServer.Entity.UserCardEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserCardController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserCardService userCardService;
    @Autowired
    UserCardRepository userCardRepository;

    @GetMapping("/userCard")
    @ResponseBody
    public ResponseEntity<String> userCardInfo(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, UserEntity.class).getUser_id();
        List<UserCardEntity> userCard = userCardRepository.findByUserId(user_id);

        String outputJson = mapper.writeValueAsString(userCard);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @Getter @Setter
    static class InJsonFormat_userCardInfo {
        private Integer user_id;
        private String store_name;
        private String category;
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

    @GetMapping("/userCard/payRecommend")
    @ResponseBody
    public ResponseEntity<String> userCardPayRecommend(@RequestBody String inputJson) throws JsonProcessingException {

        Integer user_id = mapper.readValue(inputJson, InJsonFormat_userCardInfo.class).getUser_id();
        String store_name = mapper.readValue(inputJson, InJsonFormat_userCardInfo.class).getStore_name();
        String store_category = mapper.readValue(inputJson, InJsonFormat_userCardInfo.class).getCategory();
        List<UserCardEntity> user_cards = userCardService.card_recommend_by_store(user_id, store_category, store_name);

        outJsonFormat_userCardInfo outJsonFormat;
        ArrayList<outJsonFormat_userCardInfo> outJsonFormats = new ArrayList<>();
        for(UserCardEntity user_card : user_cards) {
            outJsonFormat = new outJsonFormat_userCardInfo();

            outJsonFormat.user_id = user_card.getUser_id();
            outJsonFormat.card_id = user_card.getCard_id();
            outJsonFormat.card_name = cardRepository.get(outJsonFormat.getCard_id()).getCard_name();
            outJsonFormat.card_last_num = user_card.getCard_num().substring(15); // 맨 뒤 4자리
            outJsonFormat.discount_percentage = 10;
            outJsonFormat.card_color = cardRepository.get(outJsonFormat.getCard_id()).getCard_color();

            outJsonFormats.add(outJsonFormat);
        }

        String outputJson = mapper.writeValueAsString(outJsonFormats);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @GetMapping("/userCard/pay")
    @ResponseBody
    public ResponseEntity<ResponseEntity<byte[]>> userCardPay(@RequestBody String inputJson) throws IOException, WriterException {

        QRCode qrCode = new QRCode();
        return new ResponseEntity<>(qrCode.generate(inputJson), HttpStatus.OK);
    }
}
