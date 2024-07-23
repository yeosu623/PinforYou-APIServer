package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.Fellowship;
import YU.PinforYouAPIServer.Service.FellowshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class FellowshipController {

    @Autowired
    private FellowshipService fellowshipService;

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
}
