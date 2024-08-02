package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.PayEvent;
import YU.PinforYouAPIServer.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PayEventRepository {

    @PersistenceContext
    EntityManager em;

    public List<PayEvent> findAll() {
        return em.createQuery("select p from pay_event p", PayEvent.class)
                 .getResultList();
    }
}
