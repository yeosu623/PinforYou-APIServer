package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.ChallengeProgressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChallengeProgressController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ChallengeProgressService challengeProgressService;

    @GetMapping("/Challenge/UserProgress")
    @ResponseBody
    public ResponseEntity<String> getUserChallengeProgress(@RequestParam("user_id") Long userId) throws JsonProcessingException {
        List<Map<String, Object>> userProgress = challengeProgressService.getUserChallengeProgress(userId);
        String jsonStr = mapper.writeValueAsString(userProgress);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

}
