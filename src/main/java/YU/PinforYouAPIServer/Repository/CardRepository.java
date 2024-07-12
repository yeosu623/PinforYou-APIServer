package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CardRepository {

    @PersistenceContext
    EntityManager em;

    public Card find(Long id) {
        return em.find(Card.class, id);
    }
}
