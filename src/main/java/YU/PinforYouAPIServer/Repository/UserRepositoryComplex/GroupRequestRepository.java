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
public class GroupRequestRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public ArrayList<Integer> getGroupRequest(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getGroup_request().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getGroup_request(), new TypeReference<>() {});
    }

    public void setGroupRequest(Integer user_id, ArrayList<Integer> groupRequest) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setGroup_request(mapper.writeValueAsString(groupRequest));
    }
}
