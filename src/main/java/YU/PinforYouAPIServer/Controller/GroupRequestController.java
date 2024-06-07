package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.GroupRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupRequestController {

    @Autowired
    GroupRequestService groupRequestService;
}
