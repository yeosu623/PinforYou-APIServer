package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.ItemListEntity;
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

    @PersistenceContext
    EntityManager em;

    public UserEntity get(Integer user_id) {
        return em.find(UserEntity.class, user_id);
    }

    public void post(UserEntity userEntity) {
        em.persist(userEntity);
    }

    public void put(Integer user_id, Map<String, String> args) {
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
}
