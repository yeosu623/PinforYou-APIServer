package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.ChallengeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ChallengeRepository {

    @PersistenceContext
    EntityManager em;

    public ChallengeEntity get(Integer challenge_id) {
        return em.find(ChallengeEntity.class, challenge_id);
    }

    public void post(ChallengeEntity ChallengeEntity) {
        em.persist(ChallengeEntity);
    }

    public void put(Integer challenge_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        ChallengeEntity challenge = em.find(ChallengeEntity.class, challenge_id);

        if(args.get("challenge_name") != null) challenge.setChallenge_name(args.get("challenge_id"));
        if(args.get("challenge_info") != null) challenge.setChallenge_info(args.get("banner_image_url"));
        if(args.get("point") != null) challenge.setPoint(Integer.parseInt(args.get("card_color")));
        if(args.get("category") != null) challenge.setCategory(args.get("card_code"));
        if(args.get("cost") != null) challenge.setCost(Integer.parseInt(args.get("company")));
        if(args.get("goal") != null) challenge.setGoal(Integer.parseInt(args.get("image_url")));
    }

    public void delete(Integer challenge_id) {
        ChallengeEntity challenge = em.find(ChallengeEntity.class, challenge_id);
        em.remove(challenge);
    }
}
