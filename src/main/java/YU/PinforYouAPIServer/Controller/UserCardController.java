package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Controller.JSONFormat.ShowUserCard;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.UserCard;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
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

        // JSON을 채울때는 안쪽부터 채운다. 여기서는 배열 객체 채우기.
        List<ShowUserCard> list1 = new ArrayList<>();
        for(UserCard userCard : userCards) {
            ShowUserCard object1 = new ShowUserCard();
            object1.card_id = userCard.getCard().getId();
            object1.card_name = userCard.getCard().getCard_name();
            object1.card_num = userCard.getCard_num();

            list1.add(object1);
        }

        // JSON의 배깥쪽을 채운다. 여기서는 객체 맨 바깥의 객체 채우기.
        Map<String, List<ShowUserCard>> map1 = new HashMap<>();
        map1.put("userCard_list", list1);

        String json = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
