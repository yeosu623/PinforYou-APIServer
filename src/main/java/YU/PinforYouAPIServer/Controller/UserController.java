package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.FriendEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<String> userInfo(@RequestParam("user_id") Integer user_id) throws JsonProcessingException {
        UserEntity user = userRepository.get(user_id);

        String outputJson = mapper.writeValueAsString(user);
        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }
}
