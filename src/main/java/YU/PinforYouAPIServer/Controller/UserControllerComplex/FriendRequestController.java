package YU.PinforYouAPIServer.Controller.UserControllerComplex;

import YU.PinforYouAPIServer.Entity.UserEntityComplex.FriendEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.FriendRequestEntity;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRequestRepository;
import YU.PinforYouAPIServer.Service.UserServiceComplex.FriendRequestService;
import YU.PinforYouAPIServer.Service.UserServiceComplex.FriendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendRequestController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    FriendRepository friendRepository;
    @Autowired
    FriendService friendService;
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    FriendRequestService friendRequestService;

    @GetMapping("/user/friend/request")
    @ResponseBody
    public ResponseEntity<String> showFriendRequest(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, FriendRequestEntity.class).getUser_id();

        String outputJson = mapper.writeValueAsString(friendRequestRepository.getFriendRequest(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @PostMapping("/user/friend/request")
    @ResponseBody
    public ResponseEntity<String> addFriendRequest(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, FriendRequestEntity.class).getUser_id();
        Integer friend_id = mapper.readValue(inputJson, FriendRequestEntity.class).getFriend_id();

        friendRequestService.add_friend_request(user_id, friend_id);

        String outputJson = mapper.writeValueAsString(friendRequestRepository.getFriendRequest(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @PutMapping("/user/friend/request")
    @ResponseBody
    public ResponseEntity<String> acceptFriendRequest(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, FriendRequestEntity.class).getUser_id();
        Integer friend_id = mapper.readValue(inputJson, FriendRequestEntity.class).getFriend_id();

        friendRequestService.accept_friend_request(user_id, friend_id);

        String outputJson = mapper.writeValueAsString(friendRequestRepository.getFriendRequest(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @DeleteMapping("/user/friend/request")
    @ResponseBody
    public ResponseEntity<String> deleteFriendRequest(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, FriendRequestEntity.class).getUser_id();
        Integer friend_id = mapper.readValue(inputJson, FriendRequestEntity.class).getFriend_id();

        friendRequestService.remove_friend_request(user_id, friend_id);

        String outputJson = mapper.writeValueAsString(friendRequestRepository.getFriendRequest(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }
}
