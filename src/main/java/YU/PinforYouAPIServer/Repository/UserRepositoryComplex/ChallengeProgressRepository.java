package YU.PinforYouAPIServer.Repository.UserRepositoryComplex;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ChallengeProgressRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public ArrayList<ChallengeProgressEntity> getChallengeProgress(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getChallenge_progress().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getChallenge_progress(), new TypeReference<>() {});
    }

    public void setChallengeProgress(Integer user_id, ArrayList<ChallengeProgressEntity> challengeProgress) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setChallenge_progress(mapper.writeValueAsString(challengeProgress));
    }
}
