package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.Challenge;
import YU.PinforYouAPIServer.Entity.ChallengeProgress;
import YU.PinforYouAPIServer.Repository.ChallengeProgressRepository;
import YU.PinforYouAPIServer.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChallengeProgressService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeProgressRepository challengeProgressRepository;

    public List<Map<String, Object>> getUserChallengeProgress(Long userId) {
        List<ChallengeProgress> progressList = challengeProgressRepository.findByUserId(userId);

        return progressList.stream().map(progress -> {
            // Challenge를 가져오는 데 사용되는 메서드
            Challenge challenge = challengeRepository.findByChallengeId(progress.getChallenge().getId());

            Map<String, Object> progressMap = new HashMap<>();
            progressMap.put("challenge_name", challenge.getName());
            progressMap.put("point", challenge.getPoint());
            progressMap.put("goal", challenge.getGoal());
            progressMap.put("challenge_id", progress.getChallenge().getId()); // ID로 변경
            progressMap.put("progress", progress.getProgress());
            progressMap.put("achieved", progress.getAchieved());
            return progressMap;
        }).collect(Collectors.toList());
    }
}
