package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Other.StringAnd1DArrayConverter;
import YU.PinforYouAPIServer.Other.StringAnd2DArrayConverter;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    StringAnd1DArrayConverter stringAnd1DArrayConverter;
    StringAnd2DArrayConverter stringAnd2DArrayConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<String> getUserBasicInfo(@RequestBody UserEntity userEntity) {
        UserEntity user = userRepository.get(userEntity.getUser_id());

        JsonObject json = new JsonObject();
        json.addProperty("id", user.getUser_id());
        json.addProperty("pw", user.getPw());
        json.addProperty("tel", user.getTel());
        json.addProperty("sex", user.getSex());
        json.addProperty("age", user.getAge());
        json.addProperty("interest", user.getInterest());
        json.addProperty("point", user.getPoint());

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }

    @GetMapping("/user/friend")
    @ResponseBody
    public ResponseEntity<String> getUserFriends(@RequestBody UserEntity userEntity) {
        UserEntity user = userRepository.get(userEntity.getUser_id());

        JsonObject json = new JsonObject();
        json.addProperty("friend", );
    }
}
