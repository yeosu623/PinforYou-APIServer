package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.Fellowship;
import YU.PinforYouAPIServer.Entity.PaymentHistory;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Other.InviteCode;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.FellowshipRepository;
import YU.PinforYouAPIServer.Repository.PaymentHistoryRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Service.FellowshipService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
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
    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

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

    // 모임의 정보 전달
    // fellowship 정보 전달 -> fellowship_id를 전달 받음
    @GetMapping("/fellowship/detail")
    @ResponseBody
    public ResponseEntity<String> fellowshipDetail(@RequestParam("fellowship_id") Long fellowship_id) throws JsonProcessingException {
        Fellowship fellowship = fellowshipRepository.find(fellowship_id);
        Card card = cardRepository.find(fellowship.getCard().getId());

        Map<String, Object> result = new HashMap<>();
        result.put("fellowship_name", fellowship.getFellowship_name());
        result.put("description", fellowship.getDescription());
        result.put("leader_id", fellowship.getLeader_id());
        result.put("category", fellowship.getCategory());
        result.put("invite_code", fellowship.getInvite_code());
        result.put("card_id", fellowship.getCard().getId());
        result.put("image_url", fellowship.getCard().getImage_url());

        String jsonStr = mapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    // 모임의 카드 정보 및 결제내역 전달
    // fellowship_id를 전달받아서 결제내역, 카드 정보 전달
    @GetMapping("/fellowship/cardNpaymentDetail")
    @ResponseBody
    public ResponseEntity<String> fellowshipCardNPaymentDetail(@RequestParam("fellowship_id") Long fellowship_id) throws JsonProcessingException {
        Fellowship fellowship = fellowshipRepository.find(fellowship_id);
        Card card = cardRepository.find(fellowship.getCard().getId());
        Long user_id = fellowship.getLeader_id();
        Long card_id = fellowship.getCard().getId();

        // PaymentHistory 테이블에서 결제 내역
        List<PaymentHistory> paymentHistory = paymentHistoryRepository.findByUserAndCardId(user_id, card_id);
        List<Map<String, Object>> paymentList = new ArrayList<>();
        for (PaymentHistory payment : paymentHistory){
            Map<String, Object> history = new HashMap<>();
            history.put("pay_amount", payment.getPay_amount());
            history.put("purchase_data", payment.getPurchase_date().format(DateTimeFormatter.ISO_LOCAL_DATE));
            history.put("store_name", payment.getStore_name());
            history.put("category", payment.getCategory());

            paymentList.add(history);
        }

        // 모임 카드 정보
        List<Map<String, Object>> cardInfo = new ArrayList<>();
        Map<String, Object> cardDetail = new HashMap<>();
        cardDetail.put("card_id", card.getId());
        cardDetail.put("card_name", card.getCard_name());
        cardDetail.put("company_code", card.getCard_code());
        cardDetail.put("card_color", card.getCard_color());
        cardDetail.put("card_code", card.getCard_code());
        cardDetail.put("company", card.getCompany());
        cardDetail.put("image_url", card.getImage_url());

        cardInfo.add(cardDetail);

        Map<String, Object> result = new HashMap<>();
        result.put("card_info", cardInfo);
        result.put("payment_histoy", paymentList);

        String jsonStr = mapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    // 모임 멤버 정보 전달
    @GetMapping("/fellowship/members")
    @ResponseBody
    public ResponseEntity<String> fellowshipMembers(@RequestParam("fellowship_id") Long fellowship_id) throws JsonProcessingException {
        List<User> fellowship = fellowshipRepository.findFellowshipUsersByFellowshipId(fellowship_id);
        Fellowship fellowshipInfo = fellowshipRepository.find(fellowship_id);

        List<Map<String, Object>> fellowshipOwner = new ArrayList<>();
        List<Map<String, Object>> fellowshipMemberList = new ArrayList<>();
        for(User user : fellowship) {
            // 모임주의 id랑 다른 경우 -> 멤버
            if (!fellowshipInfo.getLeader_id().equals(user.getId())) {
                Map<String, Object> member = new HashMap<>();
                member.put("member_id", user.getId());
                member.put("member_name", user.getUsername());

                fellowshipMemberList.add(member);
            } else {
                Map<String, Object> owner = new HashMap<>();
                owner.put("member_id", user.getId());
                owner.put("member_name", user.getUsername());

                fellowshipOwner.add(owner);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("owner_info", fellowshipOwner);
        result.put("member_info", fellowshipMemberList);

        String jsonStr = mapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }
}
