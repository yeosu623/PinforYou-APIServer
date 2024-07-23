package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.Challenge;
import YU.PinforYouAPIServer.Entity.ChallengeProgress;
import YU.PinforYouAPIServer.Repository.ChallengeProgressRepository;
import YU.PinforYouAPIServer.Repository.ChallengeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChallengeDetailController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    ChallengeProgressRepository challengeProgressRepository;

    @GetMapping("/Challenge/Details")
    @ResponseBody
    public ResponseEntity<String> showChallengeDetail(@RequestParam("user_id") Long user_id,
                                                      @RequestParam("challenge_id")  Long challenge_id) throws JsonProcessingException {
        Challenge challengeInfo = challengeRepository.findByChallengeId(challenge_id);
        List<ChallengeProgress> challengeProgress = challengeProgressRepository.findByUserIdAndChallengeId(user_id, challenge_id);

        // 진행상황과 함께 세부정보 전달
        /*
        JSON 포맷
        {
            // challenge_progress 테이블에서 얻어와야하는 정보
            "challenge_progress" : [
                "progress" : 17,
                "achieved" : 0
            ],
            // challenge 테이블에서 얻어와야하는 정보
            "challenge_detail" : [
                "challenge_name" : 커피러버",
                "challenge_info" : "카페에서~",
                "point" : 2000,
                "category" : "CAFE",
                "cost" : 5000,
                "goal" : 5
            ]
        }
        */

        List<Map<String, Object>> challengeProgressList = new ArrayList<>();
        // challenge_progress
        for (ChallengeProgress progress : challengeProgress) {
            Map<String, Object> progressMap = new HashMap<>();
            progressMap.put("progress", progress.getProgress());
            progressMap.put("achieved", progress.getAchieved());
            challengeProgressList.add(progressMap);
        }

        // challengeInfo
        Map<String, Object> challengeDetails = new HashMap<>();
        challengeDetails.put("challenge_name", challengeInfo.getName());
        challengeDetails.put("challenge_info", challengeInfo.getInfo());
        challengeDetails.put("challenge_point", challengeInfo.getPoint());
        challengeDetails.put("challenge_category", challengeInfo.getCategory());
        challengeDetails.put("challenge_cost", challengeInfo.getCost());
        challengeDetails.put("challenge_goal", challengeInfo.getGoal());

        Map<String, Object> result = new HashMap<>();
        result.put("challenge_progress", challengeProgressList);
        result.put("challenge_details", challengeDetails);

        String jsonStr = mapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    /*@GetMapping("/Challenge/Detail")
    @ResponseBody
    public ResponseEntity<String> showChallengeProgress() throws JsonProcessingException {
        List<Challenge> challengeList = challengeRepository.findAll();
        // 단순 세부정보만 전달
        *//*
        JSON 포맷
        {
            "challenge_name" : "커피러버",
            "challenge_info" : "카페에서~",
            "point" : 2000,
            "category" : "CAFE",
            "cost" : 5000,
            "goal" : 5
        }
        *//*
        List<Map<String, Object>> challengeDetailsList = new ArrayList<>();

        for (Challenge challenge : challengeList) {
            Map<String, Object> map = new HashMap<>();
            map.put("challenge_name", challenge.getName());
            map.put("challenge_info", challenge.getInfo());
            map.put("challenge_point", challenge.getPoint());
            map.put("challenge_category", challenge.getCategory());
            map.put("challenge_cost", challenge.getCost());
            map.put("challenge_goal", challenge.getGoal());

            challengeDetailsList.add(map);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("challenge_list", challengeDetailsList);

        String jsonStr = mapper.writeValueAsString(result);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);

    }*/
}