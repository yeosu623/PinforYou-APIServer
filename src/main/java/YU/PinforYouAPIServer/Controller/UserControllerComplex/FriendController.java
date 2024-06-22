package YU.PinforYouAPIServer.Controller.UserControllerComplex;

import YU.PinforYouAPIServer.Entity.UserEntityComplex.FriendEntity;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRepository;
import YU.PinforYouAPIServer.Service.UserServiceComplex.FriendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class FriendController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    FriendService friendService;

    @GetMapping("/user/friend")
    public ResponseEntity<String> showFriend(@RequestParam("user_id") Integer user_id) throws JsonProcessingException {
        String outputJson = mapper.writeValueAsString(friendRepository.getFriend(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @PostMapping("/user/friend")
    @ResponseBody
    public ResponseEntity<String> addFriend(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("friend_id") Integer friend_id
    ) throws JsonProcessingException {
        friendService.add_friend(user_id, friend_id);

        String outputJson = mapper.writeValueAsString(friendRepository.getFriend(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @DeleteMapping ("/user/friend")
    @ResponseBody
    public ResponseEntity<String> deleteFriend(
            @RequestParam("user_id") Integer user_id,
            @RequestParam("friend_id") Integer friend_id
    ) throws JsonProcessingException {
        friendService.remove_friend(user_id, friend_id);

        String outputJson = mapper.writeValueAsString(friendRepository.getFriend(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }
}
