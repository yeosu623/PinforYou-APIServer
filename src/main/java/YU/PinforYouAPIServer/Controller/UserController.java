package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;
}
