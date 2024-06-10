package YU.PinforYouAPIServer.Service.UserServiceComplex;

import YU.PinforYouAPIServer.Entity.ChallengeEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Repository.ChallengeRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.ChallengeProgressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class ChallengeProgressService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ChallengeRepository challengeRepository;
    @Autowired
    ChallengeProgressRepository challengeProgressRepository;

    public void start_challenge(Integer user_id, Integer challenge_id) throws JsonProcessingException {
        ChallengeEntity challenge = challengeRepository.get(challenge_id);

        ChallengeProgressEntity challengeProgress = new ChallengeProgressEntity();
        challengeProgress.setUser_id(user_id);
        challengeProgress.setChallenge_id(challenge_id);
        challengeProgress.setProgress(0);
        challengeProgress.setGoal(challenge.getGoal());
        challengeProgress.setAchieved(false);

        ArrayList<ChallengeProgressEntity> challengeProgresses = challengeProgressRepository.getChallengeProgress(user_id);
        challengeProgresses.add(challengeProgress);
        challengeProgressRepository.setChallengeProgress(user_id, challengeProgresses);
    }

    public void increase_challenge(Integer user_id, Integer challenge_id) throws JsonProcessingException, CloneNotSupportedException {
        ArrayList<ChallengeProgressEntity> challengeProgresses = challengeProgressRepository.getChallengeProgress(user_id);

        for(ChallengeProgressEntity challengeProgress : challengeProgresses) {
            if(challengeProgress.getChallenge_id().equals(challenge_id)) {
                int progress = challengeProgress.getProgress();
                progress++;
                challengeProgress.setProgress(progress);

                if(challengeProgress.getGoal().equals(progress)) {
                    challengeProgress.setAchieved(true);
                }

                challengeProgressRepository.setChallengeProgress(user_id, challengeProgresses);
                break;
            }
        }
    }
}
