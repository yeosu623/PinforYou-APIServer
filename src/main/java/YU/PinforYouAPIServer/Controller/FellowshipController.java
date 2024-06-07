package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.FellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FellowshipController {

    @Autowired
    FellowshipService fellowshipService;
}
