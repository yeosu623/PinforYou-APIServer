package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Fellowship;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Other.InviteCode;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.FellowshipRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.FellowshipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FellowshipController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private FellowshipService fellowshipService;
    @Autowired
    private FellowshipRepository fellowshipRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;

    // 모든 Fellowship의 특정 필드만 가져오기
    @GetMapping("/fellowship")
    public List<Map<String, Object>> getAllFellowships() {
        return fellowshipService.getAllFellowshipsWithSelectedFields();
    }

    // 사용자 ID로부터 관련 Fellowship과 모든 User 정보 가져오기
    @GetMapping("/fellowship/user")
    public List<Map<String, Object>> getFellowshipDetailsByUserId(@RequestParam("user_id") Long userId) {
        List<Object[]> results = fellowshipService.getFellowshipDetailsByUserId(userId);
        return results.stream().map(row -> Map.of(
                "fellowship_name", row[0],
                "card_id", row[1],
                "category", row[2],
                "invite_code", row[3],
                "user_id", row[4],
                "nickname", row[5],
                "username", row[6]
        )).collect(Collectors.toList());
    }

    @PostMapping("/fellowship") // 모임 생성
    @ResponseBody
    public ResponseEntity<String> createFellowship(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("leader_id") Long leader_id,
            @RequestParam("card_id") Long card_id,
            @RequestParam("category") PaymentCategory category
    ) {
        Fellowship fellowship = new Fellowship();
        fellowship.setFellowship_name(name);
        fellowship.setDescription(description);
        fellowship.setLeader_id(leader_id);
        fellowship.setCard(cardRepository.find(card_id));
        fellowship.setCategory(category);
        fellowship.setInvite_code(InviteCode.generate());

        fellowshipRepository.save(fellowship);
        return new ResponseEntity<>("모임 생성이 완료되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/fellowship/invite") // 모임에 초대 가능한 친구 목록 조회
    @ResponseBody
    public ResponseEntity<String> availableUserToFellowship(@RequestParam("fellowship_id") Long fellowship_id) throws JsonProcessingException {
        Fellowship fellowship = fellowshipRepository.find(fellowship_id);
        User leader = userRepository.findOne(fellowship.getLeader_id());

        /* JSON 양식
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
        */

        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> map2 = new LinkedHashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("friend", list1);

        for(Long friend_id : leader.getFriend_ids()) {
            map2 = new LinkedHashMap<>();

            map2.put("friend_id", friend_id);
            map2.put("name", userRepository.findOne(friend_id).getNickname());

            list1.add(map2);
        }

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    // 사용자 ID와 모임 ID를 받아 해당 사용자의 fellowship_id 값을 제거
    @DeleteMapping("/fellowship/removeUser")
    public void removeUserFromFellowship(@RequestParam("fellowship_id") Long fellowship_id, @RequestParam("user_id") Long user_id) {
        fellowshipService.removeUserFromFellowship(fellowship_id, user_id);
    }

    @PostMapping("/fellowship/request")
    public void requestFellowship(@RequestParam("user_id") Long user_id, @RequestParam("fellowship_id") Long fellowship_id) {
        fellowshipService.requestFellowship(user_id, fellowship_id);
    }
}
