package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.CardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Repository
public class CardRepository {

    @PersistenceContext
    EntityManager em;

    public CardEntity get(Integer card_id) {
        return em.find(CardEntity.class, card_id);
    }

    public void post(CardEntity CardEntity) {
        em.persist(CardEntity);
    }

    public void put(Integer card_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        CardEntity card = em.find(CardEntity.class, card_id);

        if(args.get("card_name") != null) card.setCard_name(args.get("card_id"));
        if(args.get("company_code") != null) card.setCompany_code(args.get("banner_image_url"));
        if(args.get("card_color") != null) card.setCard_color(args.get("card_color"));
        if(args.get("card_code") != null) card.setCard_code(args.get("card_code"));
        if(args.get("company") != null) card.setCompany(args.get("company"));
        if(args.get("image_url") != null) card.setImage_url(args.get("image_url"));
    }

    public void delete(Integer card_id) {
        CardEntity card = em.find(CardEntity.class, card_id);
        em.remove(card);
    }
}
