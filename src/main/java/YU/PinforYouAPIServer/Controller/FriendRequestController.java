package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendRequestController {

    @Autowired
    FriendRequestService friendRequestService;
}
