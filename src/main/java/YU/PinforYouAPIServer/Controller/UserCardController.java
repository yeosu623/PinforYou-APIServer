package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Algorithm.IdentifyCardNumberAlgorithm;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.UserCard;
import YU.PinforYouAPIServer.Other.HandleCardNumber;
import YU.PinforYouAPIServer.Other.QRCode;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class UserCardController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCardService userCardService;
    @Autowired
    UserCardRepository userCardRepository;
    @Autowired
    IdentifyCardNumberAlgorithm identifyCardNumberAlgorithm;

    @GetMapping("/userCard")
    @ResponseBody
    public ResponseEntity<String> showUserCard(@RequestParam("user_id") Long user_id) throws JsonProcessingException {
        List<UserCard> userCards = userRepository.findOne(user_id).getUser_cards();

        /*
        JSON 포맷
        {
            "userCard_list": [
                {
                    "card_id": 3,
                    "card_name": "The BEST-F",
                    "card_num": "1736-6684-3090-2003"
                },
                {
                    "card_id": 7,
                    "card_name": "굿데이올림",
                    "card_num": "3357-8390-0054-9183"
                }
            ]
        }
         */
        // json 포맷 생성
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("userCard_list", list1);

        for(UserCard userCard : userCards) {
            map2 = new HashMap<>();
            map2.put("card_id",   userCard.getCard().getId());
            map2.put("card_name", userCard.getCard().getCard_name());
            map2.put("card_num",  userCard.getCard_num());

            list1.add(map2);
        }

        // json을 string으로 변환한뒤 반환
        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PostMapping("/userCard")
    @ResponseBody
    public ResponseEntity<String> addUserCard(
            @RequestParam("user_id") Long user_id,
            @RequestParam("card_number") String card_number,
            @RequestParam("card_name") String card_name
    ) throws JsonProcessingException {
        UserCard userCard = new UserCard();

        userCard.setUser(userRepository.findOne(user_id));
        userCard.setCard(identifyCardNumberAlgorithm.calculate(card_number));
        userCard.setCard_num(HandleCardNumber.split(card_number));
        userCard.setCard_name(card_name);

        userCardRepository.save(userCard);

        /* JSON 포맷
        {
            result = true
        }
         */

        Map<String, Object> map1 = new HashMap<>();

        map1.put("result", true);

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @DeleteMapping("/userCard")
    @ResponseBody
    public ResponseEntity<String> deleteUserCard(
            @RequestParam("user_id") Long user_id,
            @RequestParam("card_id") Long card_id
    ) throws JsonProcessingException {
        UserCard userCard = userCardRepository.findByUserAndCardId(user_id, card_id);

        userCardRepository.delete(userCard);

        /* JSON 포맷
        {
            result = true
        }
        */

        Map<String, Object> map1 = new HashMap<>();

        map1.put("result", true);

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    /*
    미완성 : 결제 추천 알고리즘 적용 필요
     */
    @GetMapping("/userCard/payRecommend")
    @ResponseBody
    public ResponseEntity<String> userCardPayRecommend(
            @RequestParam("user_id") Long user_id,
            @RequestParam("store_name") String store_name,
            @RequestParam("category") String category
    ) throws JsonProcessingException {
        List<UserCard> userCards = userRepository.findOne(user_id).getUser_cards();

        /* JSON 포맷
        {
            "card_list": [
                {
                    "user_id": 1,
                    "card_id": 3,
                    "card_name": "The BEST-F",
                    "card_last_num": "2003",
                    "discount_percentage": 10,
                    "card_color": "665F93"
                },
                {
                    "user_id": 1,
                    "card_id": 7,
                    "card_name": "굿데이올림",
                    "card_last_num": "9183",
                    "discount_percentage": 7,
                    "card_color": "DE7B10"
                }
            ]
        }
        */

        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("card_list", list1);

        for(UserCard userCard : userCards) {
            map2 = new HashMap<>();
            map2.put("user_id", userCard.getUser().getId());
            map2.put("card_id", userCard.getCard().getId());
            map2.put("card_name", userCard.getCard().getCard_name());
            map2.put("card_last_num", userCard.getCard_num().substring(15));
            map2.put("discount_percentage", -1); // 알고리즘 적용 필요
            map2.put("card_color", userCard.getCard().getCard_color());

            list1.add(map2);
        }

        // json을 string으로 변환한뒤 반환
        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @GetMapping("/userCard/newCardRecommend") // 발급 추천
    @ResponseBody
    public ResponseEntity<String> showNewCardRecommend(@RequestParam("user_id") Long user_id) throws JsonProcessingException {
        Card card = userCardService.newCardRecommend(user_id);

        /* JSON 포맷
        {
           "category": 카페
           "name": 김성훈이 최고야 카드(Black)
           "image_url": "https://..."
           "benefits": [
                "커피, 모바일, 문화 10% 할인",
                "뷰티, 편의점 5% 할인"
           ]
        }
        */

        Map<String, Object> map1 = new LinkedHashMap<>();

        map1.put("category", card.getMajor_benefit());
        map1.put("name", card.getCard_name());
        map1.put("image_url", card.getImage_url());
        map1.put("benefits", card.getBenefits_description());

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    /*
    미완성 : QR코드에 있는 Base64 인코딩 제거 필요
     */
    @GetMapping("/userCard/pay")
    @ResponseBody
    public ResponseEntity<ResponseEntity<byte[]>> userCardPay(
            @RequestParam("user_id") Long user_id,
            @RequestParam("card_id") Long card_id
    ) throws IOException, WriterException {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("user_id", user_id);
        map1.put("card_id", card_id);

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(QRCode.generate(jsonStr), HttpStatus.OK);
    }
}
