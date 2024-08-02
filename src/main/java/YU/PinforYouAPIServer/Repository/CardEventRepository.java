package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.CardEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardEventRepository {

    @PersistenceContext
    EntityManager em;

    public List<CardEvent> findAll() {
        return em.createQuery("select c from card_event c", CardEvent.class)
                 .getResultList();
    }
}
