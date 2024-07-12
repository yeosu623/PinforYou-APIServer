package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.PaymentHistory;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Entity.UserCard;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.PaymentHistoryRepository;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class PaymentHistoryController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserCardRepository userCardRepository;
    @Autowired
    PaymentHistoryRepository paymentHistoryRepository;

    @GetMapping("/paymentHistory")
    @ResponseBody
    public ResponseEntity<String> getPaymentHistory(
            @RequestParam("user_id") Long user_id,
            @RequestParam("card_id") Long card_id
    ) throws JsonProcessingException {
        UserCard userCard = userCardRepository.findByUserAndCardId(user_id, card_id);
        List<PaymentHistory> histories = paymentHistoryRepository.findByUserAndCardId(user_id, card_id);

        /* JSON 포맷
        {
            "user_id": 1,
            "card_id": 14,
            "card_name": "사직서",
            "card_num": "9412-2198-7123-0090",
            "card_color": "593C31",
            "payments": [
                {
                    "pay_amount": 3900,
                    "purchase_date": "2024-06-22",
                    "store_name": "탕후루잘하는오빠",
                    "category": "FOOD"
                },
                {
                    "pay_amount": 5990,
                    "purchase_date": "2024-06-22",
                    "store_name": "아틀리에",
                    "category": "CAFE"
                }
            ]
        }
         */

        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> map2 = new LinkedHashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("user_id", user_id);
        map1.put("card_id", card_id);
        map1.put("card_name", userCard.getCard().getCard_name());
        map1.put("card_num", userCard.getCard_num());
        map1.put("card_color", userCard.getCard().getCard_color());
        map1.put("payments", list1);

        for(PaymentHistory history : histories) {
            map2 = new LinkedHashMap<>();
            map2.put("pay_amount", history.getPay_amount());
            map2.put("purchase_date", history.getPurchase_date().format(DateTimeFormatter.ISO_LOCAL_DATE));
            map2.put("store_name", history.getStore_name());
            map2.put("category", history.getCategory());

            list1.add(map2);
        }

        // json을 string으로 변환한뒤 반환
        String jsonStr = mapper.registerModule(new JavaTimeModule()).writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    /*
    미완성 : 실제 할인된 가격을 기록에 저장하도록 수정할 것.
     */
    @PostMapping("/paymentHistory")
    @ResponseBody
    public ResponseEntity<String> savePaymentHistory (
            @RequestParam("user_id") Long user_id,
            @RequestParam("card_id") Long card_id,
            @RequestParam("pay_amount") Long pay_amount,
            @RequestParam("store_name") String store_name,
            @RequestParam("category") PaymentCategory category
    ) {
        User user = userRepository.findOne(user_id);
        Card card = cardRepository.find(card_id);

        PaymentHistory history = new PaymentHistory();
        history.setUser(user);
        history.setCard(card);
        history.setPay_amount(pay_amount);
        history.setPurchase_date(LocalDateTime.now());
        history.setStore_name(store_name);
        history.setCategory(category);
        paymentHistoryRepository.save(history);

        return new ResponseEntity<>("결제 내역 저장 완료", HttpStatus.OK);
    }
}
