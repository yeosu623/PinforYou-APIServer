package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.CardEvent;
import YU.PinforYouAPIServer.Entity.PayEvent;
import YU.PinforYouAPIServer.Repository.CardEventRepository;
import YU.PinforYouAPIServer.Repository.PayEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PayEventRepository payEventRepository;
    @Autowired
    CardEventRepository cardEventRepository;

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<String> showEvent() throws JsonProcessingException {
        List<CardEvent> cardEvents = cardEventRepository.findAll();
        List<PayEvent> payEvents = payEventRepository.findAll();

        /* JSON 포맷
        {
            "card_event" : [
                https://image1...
                https://image2...
            ]
            "pay_event" : [
                https://image1...
                https://image2...
            ]
        }
         */

        Map<String, Object> map1 = new LinkedHashMap<>();
        List<Object> list1 = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();

        for (CardEvent cardEvent : cardEvents) {
            list1.add(cardEvent.getBanner_image_url());
        }
        for (PayEvent payEvent : payEvents) {
            list2.add(payEvent.getBanner_image_url());
        }

        map1.put("card_event", list1);
        map1.put("pay_event", list2);

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }
}
