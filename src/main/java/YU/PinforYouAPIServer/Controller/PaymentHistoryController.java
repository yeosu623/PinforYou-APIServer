package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.CardEntity;
import YU.PinforYouAPIServer.Entity.PaymentHistoryEntity;
import YU.PinforYouAPIServer.Entity.UserCardEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.PaymentHistoryRepository;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
import YU.PinforYouAPIServer.Service.PaymentHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PaymentHistoryController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PaymentHistoryService paymentHistoryService;
    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserCardRepository userCardRepository;


    @Getter @Setter
    static class InJsonFormat_getPaymentHistory {
        private Integer user_id;
        private Integer card_id;
    }
    @Getter @Setter
    static class OutJsonFormat_getPaymentHistory {
        private Integer user_id;
        private Integer card_id;
        private String card_name;
        private String card_num;
        private String card_color;

        List<OutJsonFormat_getPaymentHistory_Inner> payments = new ArrayList<>();
    }
    @Getter @Setter
    static class OutJsonFormat_getPaymentHistory_Inner {
        private Integer pay_amount;
        private String purchase_date;
        private String store_name;
        private String category;
    }

    @GetMapping("/paymentHistory")
    @ResponseBody
    public ResponseEntity<String> getPaymentHistory(@RequestBody String inputJson) throws JsonProcessingException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Integer user_id = mapper.readValue(inputJson, InJsonFormat_getPaymentHistory.class).getUser_id();
        Integer card_id = mapper.readValue(inputJson, InJsonFormat_getPaymentHistory.class).getCard_id();
        List<PaymentHistoryEntity> paymentHistories = paymentHistoryRepository.findByUserAndCardId(user_id, card_id);

        OutJsonFormat_getPaymentHistory outJsonFormat = new OutJsonFormat_getPaymentHistory();
        outJsonFormat.setUser_id(user_id);
        outJsonFormat.setCard_id(card_id);
        outJsonFormat.setCard_name(cardRepository.get(card_id).getCard_name());
        outJsonFormat.setCard_num(userCardRepository.findByUserAndCardId(user_id, card_id).getCard_num());
        outJsonFormat.setCard_color(cardRepository.get(card_id).getCard_color());
        for(PaymentHistoryEntity paymentHistory : paymentHistories) {
            OutJsonFormat_getPaymentHistory_Inner inner = new OutJsonFormat_getPaymentHistory_Inner();
            inner.setPay_amount(paymentHistory.getPay_amount());
            inner.setPurchase_date(dateFormat.format(paymentHistory.getPurchase_date()));
            inner.setStore_name(paymentHistory.getStore_name());
            inner.setCategory(paymentHistory.getCategory());

            outJsonFormat.payments.add(inner);
        }

        String outputJson = mapper.writeValueAsString(outJsonFormat);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @PostMapping("/paymentHistory")
    @ResponseBody
    public ResponseEntity<Void> savePaymentHistory(@RequestBody String inputJson) throws JsonProcessingException {

        PaymentHistoryEntity payment = new PaymentHistoryEntity();
        payment.setUser_id(mapper.readValue(inputJson, PaymentHistoryEntity.class).getUser_id());
        payment.setCard_id(mapper.readValue(inputJson, PaymentHistoryEntity.class).getCard_id());
        payment.setPay_amount(mapper.readValue(inputJson, PaymentHistoryEntity.class).getPay_amount());
        payment.setPurchase_date(mapper.readValue(inputJson, PaymentHistoryEntity.class).getPurchase_date());
        payment.setStore_name(mapper.readValue(inputJson, PaymentHistoryEntity.class).getStore_name());
        payment.setCategory(mapper.readValue(inputJson, PaymentHistoryEntity.class).getCategory());

        paymentHistoryRepository.post(payment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
