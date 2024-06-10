package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

}
