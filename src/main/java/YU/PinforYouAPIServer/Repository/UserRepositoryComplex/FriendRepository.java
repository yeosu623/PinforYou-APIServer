package YU.PinforYouAPIServer.Repository.UserRepositoryComplex;

import YU.PinforYouAPIServer.Entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FriendRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public ArrayList<Integer> getFriend(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getFriend().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getFriend(), new TypeReference<>() {});
    }

    public void setFriend(Integer user_id, ArrayList<Integer> friends) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setFriend(mapper.writeValueAsString(friends));
    }
}
