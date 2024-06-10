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
public class FriendRequestRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public ArrayList<Integer> getFriendRequest(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getFriend_request().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getFriend_request(), new TypeReference<>() {});
    }

    public void setFriendRequest(Integer user_id, ArrayList<Integer> friendRequest) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setFriend_request(mapper.writeValueAsString(friendRequest));
    }
}
