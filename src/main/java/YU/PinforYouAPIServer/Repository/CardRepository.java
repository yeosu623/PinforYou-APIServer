package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.UserCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CardRepository {

    @PersistenceContext
    EntityManager em;

    public Card find(Long id) {
        return em.find(Card.class, id);
    }

    public List<Card> findByCategory(PaymentCategory category) {
        return em.createQuery("select c from card c where c.major_benefit = :category", Card.class)
                .setParameter("category", category)
                .getResultList();
    }
}
