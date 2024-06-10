package YU.PinforYouAPIServer.Controller.UserControllerComplex;

import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.ChallengeProgressRepository;
import YU.PinforYouAPIServer.Service.UserService;
import YU.PinforYouAPIServer.Service.UserServiceComplex.ChallengeProgressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChallengeProgressController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ChallengeProgressRepository challengeProgressRepository;
    @Autowired
    ChallengeProgressService challengeProgressService;

    @GetMapping("user/challenge")
    @ResponseBody
    public ResponseEntity<String> showChallenge(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, ChallengeProgressEntity.class).getUser_id();

        String outputJson = mapper.writeValueAsString(challengeProgressRepository.getChallengeProgress(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }


    @PostMapping("user/challenge")
    @ResponseBody
    public ResponseEntity<String> startChallenge(@RequestBody String inputJson) throws JsonProcessingException {
        Integer user_id = mapper.readValue(inputJson, ChallengeProgressEntity.class).getUser_id();
        Integer challenge_id = mapper.readValue(inputJson, ChallengeProgressEntity.class).getChallenge_id();

        challengeProgressService.start_challenge(user_id, challenge_id);

        String outputJson = mapper.writeValueAsString(challengeProgressRepository.getChallengeProgress(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }

    @PutMapping("user/challenge")
    @ResponseBody
    public ResponseEntity<String> increase_challenge(@RequestBody String inputJson) throws JsonProcessingException, CloneNotSupportedException {
        Integer user_id = mapper.readValue(inputJson, ChallengeProgressEntity.class).getUser_id();
        Integer challenge_id = mapper.readValue(inputJson, ChallengeProgressEntity.class).getChallenge_id();

        challengeProgressService.increase_challenge(user_id, challenge_id);

        String outputJson = mapper.writeValueAsString(challengeProgressRepository.getChallengeProgress(user_id));

        return new ResponseEntity<>(outputJson, HttpStatus.OK);
    }
}
