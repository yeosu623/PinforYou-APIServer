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

import java.util.*;

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

    @PostMapping("/fellowship/accept")
    public ResponseEntity<String> acceptFellowshipRequest(@RequestBody AcceptFellowshipRequest request) {
        boolean isAccepted = userService.acceptFellowshipRequest(request.getUser_id(), request.getAcceptedRequests());
        if (isAccepted) {
            return ResponseEntity.ok("Fellowship requests accepted successfully");
        } else {
            return ResponseEntity.status(400).body("Failed to accept fellowship requests");
        }
    }

    // 앱 접속시 프론트로 유저의 정보 전달
    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<String> getUserLoginInfo(@RequestParam("user_id") Long user_id) throws JsonProcessingException {
        User user = userRepository.findOne(user_id);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user_id", user_id);
        userInfo.put("name", user.getUsername());
        userInfo.put("tel", user.getTel());
        userInfo.put("email", user.getEmail());

        String jsonStr = mapper.writeValueAsString(userInfo);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    // 사용자 친구 추가
    @PostMapping("/friend/add")
    @ResponseBody
    public ResponseEntity<String> addFriend(@RequestParam("user_id") Long user_id,
                                            @RequestParam("friend_id") Long friend_id) throws JsonProcessingException {
        User user = userRepository.findOne(user_id);
        User friend = userRepository.findOne(friend_id);

        if (friend == null) {
            // 친구의 아이디가 존재하지 않음
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("message", "User not found");
            result.put("requests", 0);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        }

        // 친구추가 요청한 friend_id가 이미 user_id의 friend_ids 항목에 존재하는경우
        if (user.getFriend_ids().contains(friend_id)) {
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("message", "Already exists friend id");
            result.put("requests", 0);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        } else if (user.getPending_request_ids().contains(friend_id)) {
            // 친구추가 요청한 friend_id가 이미 user_id의 pending_request_ids 항목에 존재하는경우
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("message", "Already exists request friend id");
            result.put("requests", 0);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        } else {
            // user_id의 pending_requestIds 배열에 friend_id 삽입
            ArrayList<List<Long>> user_pending_requestIds = new ArrayList<>();
            user_pending_requestIds.add(user.getPending_request_ids());
            user_pending_requestIds.get(0).add(friend_id);
            userRepository.save(user);

            // friend_id의 request_ids배열에 user_id 삽입
            ArrayList<List<Long>> friend_request = new ArrayList<>();
            friend_request.add(friend.getFriend_request());
            friend_request.get(0).add(user_id);
            userRepository.save(friend);

            Map<String, Object> result = new HashMap<>();
            result.put("user_id", user_id);
            result.put("friend_id", friend_id);
            result.put("requests", 1);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        }
    }


    // 친구 수락
    @PostMapping("/friend/requestList/accept")
    @ResponseBody
    public ResponseEntity<String> acceptFriendRequest(@RequestParam("user_id") Long user_id,
                                                      @RequestParam("friend_id") Long friend_id) throws JsonProcessingException {
        User user = userRepository.findOne(user_id);
        User friend = userRepository.findOne(friend_id);

        // pending_request_ids 배열에 존재하지 않는 id인 경우
        if (!user.getFriend_request().contains(friend_id)) {
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("message", "Not exists request friend id");
            result.put("acceptFriend", 0);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        } else {
            /* user_id -> friend_id : 친구 요청 수락 */
            ArrayList<List<Long>> user_requestList = new ArrayList<>();
            // 요청에서 삭제
            user_requestList.add(user.getFriend_request());
            user_requestList.get(0).remove(friend_id);
            // friend_ids 목록으로 추가
            user_requestList.add(user.getFriend_ids());
            user_requestList.get(1).add(friend_id);
            userRepository.save(user);

            /* friend_id -> user_id : 친구 추가 성공 */
            ArrayList<List<Long>> friend_requests = new ArrayList<>();
            // pending_request_ids 배열에서 요청한 id 삭제
            friend_requests.add(friend.getPending_request_ids());
            friend_requests.get(0).remove(user_id);
            // friend_ids 배열에 추가
            friend_requests.add(friend.getFriend_ids());
            friend_requests.get(1).add(user_id);
            userRepository.save(friend);


            Map<String, Object> result = new HashMap<>();
            result.put("user_id", user_id);
            result.put("friend_id", friend_id);
            result.put("message", "Adding friends is a success");
            result.put("acceptFriend", 1);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        }
    }
}
