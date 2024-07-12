package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;


}
