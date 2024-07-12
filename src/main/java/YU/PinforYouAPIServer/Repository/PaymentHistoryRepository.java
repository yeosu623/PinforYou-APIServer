package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.PaymentHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PaymentHistoryRepository {

    @PersistenceContext
    EntityManager em;

    public void save(PaymentHistory paymentHistory) {
        em.persist(paymentHistory);
    }

    public List<PaymentHistory> findByUserAndCardId(Long user_id, Long card_id) {
        return em.createQuery("select p from payment_history p where p.user.id = :user_id and p.card.id = :card_id", PaymentHistory.class)
                .setParameter("user_id", user_id)
                .setParameter("card_id", card_id)
                .getResultList();
    }
}
