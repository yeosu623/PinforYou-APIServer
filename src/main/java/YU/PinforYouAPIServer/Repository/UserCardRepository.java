package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.PaymentHistory;
import YU.PinforYouAPIServer.Entity.UserCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserCardRepository {

    @PersistenceContext
    EntityManager em;

    public void save(UserCard userCard) {
        em.persist(userCard);
    }

    public void delete(UserCard userCard) {
        em.remove(userCard);
    }

    public UserCard findByUserAndCardId(Long user_id, Long card_id) {
        return em.createQuery("select u from user_card u where u.user.id= :user_id and u.card.id = :card_id", UserCard.class)
                .setParameter("user_id", user_id)
                .setParameter("card_id", card_id)
                .getSingleResult();
    }
}
