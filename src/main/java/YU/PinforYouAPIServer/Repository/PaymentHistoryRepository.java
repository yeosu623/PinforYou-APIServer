package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.PaymentHistoryEntity;
import YU.PinforYouAPIServer.Entity.UserCardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentHistoryRepository {

    @PersistenceContext
    EntityManager em;

    public PaymentHistoryEntity get(Integer payment_id) {
        return em.find(PaymentHistoryEntity.class, payment_id);
    }

    public void post(PaymentHistoryEntity PaymentHistoryEntity) {
        em.persist(PaymentHistoryEntity);
    }

    public void put(Integer payment_id, Map<String, String> args) throws ParseException {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        PaymentHistoryEntity payment = em.find(PaymentHistoryEntity.class, payment_id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if(args.get("user_id") != null) payment.setUser_id(Integer.parseInt(args.get("user_id")));
        if(args.get("card_id") != null) payment.setCard_id(Integer.parseInt(args.get("card_id")));
        if(args.get("pay_amount") != null) payment.setPay_amount(Integer.parseInt(args.get("pay_amount")));
        if(args.get("purchase_date") != null) payment.setPurchase_date(format.parse(args.get("user_id")));
        if(args.get("store_name") != null) payment.setStore_name(args.get("store_name"));
        if(args.get("category") != null) payment.setCategory(args.get("category"));

    }

    public void delete(Integer payment_id) {
        PaymentHistoryEntity payment = em.find(PaymentHistoryEntity.class, payment_id);
        em.remove(payment);
    }

    public List<PaymentHistoryEntity> findByUserAndCardId(Integer user_id, Integer card_id) {
        return em.createQuery("select p from payment_history p where p.user_id = :user_id and p.card_id = :card_id", PaymentHistoryEntity.class)
                .setParameter("user_id", user_id)
                .setParameter("card_id", card_id)
                .getResultList();
    }
}
