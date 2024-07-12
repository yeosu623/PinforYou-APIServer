package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.UserCard;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserCardController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserCardService userCardService;
    @Autowired
    UserRepository userRepository;

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
}
