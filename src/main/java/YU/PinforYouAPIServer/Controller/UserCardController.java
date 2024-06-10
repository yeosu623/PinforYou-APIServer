package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.UserCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCardController {

    @Autowired
    UserCardService userCardService;
}
