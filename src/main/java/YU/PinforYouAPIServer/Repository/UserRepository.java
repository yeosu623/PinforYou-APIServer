package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserEntity_Complex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public class UserRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public UserEntity get(Integer user_id) {
        return em.find(UserEntity.class, user_id);
    }

    public void post(UserEntity userEntity) {
        em.persist(userEntity);
    }

    public void put_basic_info(Integer user_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        UserEntity user = em.find(UserEntity.class, user_id);

        if(args.get("username") != null) user.setUsername(args.get("username"));
        if(args.get("id") != null) user.setId(args.get("id"));
        if(args.get("pw") != null) user.setPw(args.get("pw"));
        if(args.get("tel") != null) user.setTel(args.get("tel"));
        if(args.get("sex") != null) user.setSex(args.get("sex"));
        if(args.get("age") != null) user.setAge(Integer.parseInt(args.get("age")));
        if(args.get("interest") != null) user.setInterest(args.get("interest"));
        if(args.get("point") != null) user.setPoint(Integer.parseInt(args.get("point")));

        // array형 컬럼은 지원하지 않음.
    }

    public void delete(Integer user_id) {
        UserEntity user = em.find(UserEntity.class, user_id);
        em.remove(user);
    }

    // array형 컬럼을 위한 get, set 메서드
    public ArrayList<Integer> getFriend_useThis(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getFriend().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getFriend(), new TypeReference<>() {});
    }

    public void setFriend_useThis(Integer user_id, ArrayList<Integer> friends) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setFriend(mapper.writeValueAsString(friends));
    }

    public ArrayList<ChallengeProgressEntity> getChallengeProgress_useThis(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        return mapper.readValue(user.getChallenge_progress(), new TypeReference<>() {});
    }

    public void setChallengeProgress_useThis(Integer user_id, ArrayList<ChallengeProgressEntity> challengeProgress) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setChallenge_progress(mapper.writeValueAsString(challengeProgress));
    }

    public ArrayList<Integer> getItemList_useThis(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        return mapper.readValue(user.getItem_list(), new TypeReference<>() {});
    }

    public void setItemList_useThis(Integer user_id, ArrayList<Integer> itemList) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setItem_list(mapper.writeValueAsString(itemList));
    }

    public ArrayList<Integer> getFriendRequest_useThis(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        return mapper.readValue(user.getFriend_request(), new TypeReference<>() {});
    }

    public void setFriendRequest_useThis(Integer user_id, ArrayList<Integer> friendRequest) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setItem_list(mapper.writeValueAsString(friendRequest));
    }

    public ArrayList<Integer> getGroupRequest_useThis(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        return mapper.readValue(user.getGroup_request(), new TypeReference<>() {});
    }

    public void setGroupRequest_useThis(Integer user_id, ArrayList<Integer> groupRequest) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setGroup_request(mapper.writeValueAsString(groupRequest));
    }
}
