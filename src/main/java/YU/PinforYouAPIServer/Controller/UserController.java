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
    @Autowired
    UserService userService;


    @GetMapping("/friend")
    @ResponseBody
    public ResponseEntity<String> showFriends(@RequestParam("user_id") Long user_id) throws JsonProcessingException {
        User user = userRepository.findOne(user_id);

        /* JSON 포맷
        {
            "friend": [
                {
                    "friend_id": 2
                    "name": "이준수"
                },
                {
                    "friend_id": 4
                    "name": "박태현"
                },
                {
                    "friend_id": 9
                    "name": "김이박"
                }
            ]
        }
        */

        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> map2 = new LinkedHashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("friend", list1);

        for(Long friend_id : user.getFriend_ids()) {
            map2 = new LinkedHashMap<>();

            map2.put("friend_id", friend_id);
            map2.put("name", userRepository.findOne(friend_id).getNickname());

            list1.add(map2);
        }

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PostMapping("/user/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
        boolean isChanged = userService.changePassword(request.getUser_id(), request.getOldPassword(), request.getNewPassword());
        if (isChanged) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(400).body("Incorrect old password");
        }
    }
}
