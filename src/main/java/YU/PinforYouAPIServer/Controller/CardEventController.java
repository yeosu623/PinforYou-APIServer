package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.CardEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardEventController {

    @Autowired
    CardEventService cardEventService;
}
