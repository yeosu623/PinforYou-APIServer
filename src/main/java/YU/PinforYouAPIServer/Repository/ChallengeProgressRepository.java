package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.ChallengeProgress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ChallengeProgressRepository {

    @PersistenceContext
    EntityManager em;

    // use_id에 해당하는 챌린지 항목 가져오기
    public List<ChallengeProgress> findByUserIdAndChallengeId(Long user_id, Long challenge_id) {
        return em.createQuery("select cp from challenge_progress cp where cp.user.id=:user_id and cp.challenge.id=:challenge_id", ChallengeProgress.class)
                .setParameter("user_id", user_id)
                .setParameter("challenge_id", challenge_id)
                .getResultList();
    }

    // Optional 사용
    /*public Optional<ChallengeProgress> findByUserIdAndChallengeId(Long userId, Long challengeId) {
        List<ChallengeProgress> results = em.createQuery("select cp from challenge_progress cp where cp.user.id = :userId and cp.challenge.id = :challengeId", ChallengeProgress.class)
                .setParameter("userId", userId)
                .setParameter("challengeId", challengeId)
                .getResultList();

        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }*/

    /*public Challenge findByUserId(Long user_id) {
        return em.createQuery("select c from challenge c where c.id= :challenge_id", Challenge.class)
                .setParameter("challenge_id", challenge_id)
                .getSingleResult();
    }*/

    // user_id에 해당하는 챌린지 진행 상황 가져오기
    public List<ChallengeProgress> findByUserId(Long userId) {
        return em.createQuery("SELECT cp FROM challenge_progress cp WHERE cp.user.id = :userId", ChallengeProgress.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
