package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserCardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserCardRepository {

    @PersistenceContext
    EntityManager em;

    public UserCardEntity get(Integer user_card_id) {
        return em.find(UserCardEntity.class, user_card_id);
    }

    public void post(UserCardEntity UserCardEntity) {
        em.persist(UserCardEntity);
    }

    public void put(Integer user_card_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        UserCardEntity user_card = em.find(UserCardEntity.class, user_card_id);

        if(args.get("user_id") != null) user_card.setUser_id(Integer.parseInt(args.get("user_id")));
        if(args.get("card_id") != null) user_card.setCard_id(Integer.parseInt(args.get("card_id")));
        if(args.get("card_num") != null) user_card.setCard_num(args.get("sex"));
    }

    public void delete(Integer user_card_id) {
        UserCardEntity user_card = em.find(UserCardEntity.class, user_card_id);
        em.remove(user_card);
    }

    public List<UserCardEntity> findByUserId(Integer user_id) {
        return em.createQuery("select u from user_card u where u.user_id = user_id", UserCardEntity.class).getResultList();
    }
}
