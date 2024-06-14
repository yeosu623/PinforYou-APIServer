package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.PaymentHistoryEntity;
import YU.PinforYouAPIServer.Entity.UserCardEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Repository.PaymentHistoryRepository;
import YU.PinforYouAPIServer.Service.PaymentHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 작업중
//    @GetMapping("/paymentHistory")
//    @ResponseBody
//    public ResponseEntity<String> getPaymentHistory(@RequestBody String inputJson) throws JsonProcessingException {
//
//        @Getter @Setter
//        class InJsonFormat { // 로컬 클래스. 메서드 밖에서 접근 불가
//            private Integer user_id;
//            private Integer card_id;
//        }
//        Integer user_id = mapper.readValue(inputJson, InJsonFormat.class).getUser_id();
//        Integer card_id = mapper.readValue(inputJson, InJsonFormat.class).getUser_id();
//        List<PaymentHistoryEntity> payments = paymentHistoryRepository.findByUserAndCardId(user_id, card_id);
//
//        @Getter @Setter
//        class OutJsonFormat { // 로컬 클래스. 메서드 밖에서 접근 불가
//            private Integer user_id;
//            private Integer card_id;
//            private String card_name;
//            private String card_num;
//            private String card_color;
//
//            List<OutJsonFormat_inner> payments;
//            static class OutJsonFormat_inner {
//                private Integer pay_amount;
//                private Date purchase_date;
//                private String store_name;
//                private String category;
//            }
//        }
//        OutJsonFormat outJsonFormat = new OutJsonFormat();
//        outJsonFormat.user_id = user_id;
//        outJsonFormat.card_id = card_id;
//        outJsonFormat.card_name = payments.
//        List<OutJsonFormat> outJsonFormats = new ArrayList<>();
//        for(PaymentHistoryEntity payment : payments) {
//
//            outJsonFormats.add(outJsonFormat);
//        }
//
//        String outputJson = mapper.writeValueAsString(outJsonFormats);
//        return new ResponseEntity<>(outputJson, HttpStatus.OK);
//    }
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
