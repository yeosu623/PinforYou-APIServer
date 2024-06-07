package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

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

    public void put(Integer user_id, Map<String, Object> args) {
        UserEntity user = em.find(UserEntity.class, user_id);

        if(args.get("id") != null ) user.setId(args.get("id").toString());
        // ...
    }

    public void delete(Integer user_id) {
        UserEntity user = em.find(UserEntity.class, user_id);
        em.remove(user);
    }
}
