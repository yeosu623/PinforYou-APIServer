package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.CardEventEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Repository
public class CardEventRepository {

    @PersistenceContext
    EntityManager em;

    public CardEventEntity get(Integer event_id) {
        return em.find(CardEventEntity.class, event_id);
    }

    public void post(CardEventEntity CardEventEntity) {
        em.persist(CardEventEntity);
    }

    public void put(Integer event_id, Map<String, String> args) throws ParseException {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        CardEventEntity event = em.find(CardEventEntity.class, event_id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if(args.get("card_id") != null) event.setCard_id(Integer.parseInt(args.get("card_id")));
        if(args.get("banner_image_url") != null) event.setBanner_image_url(args.get("banner_image_url"));
        if(args.get("content_image_url") != null) event.setContent_image_url(args.get("content_image_url"));
        if(args.get("start_date") != null) event.setStart_date(format.parse(args.get("start_date")));
        if(args.get("end_date") != null) event.setEnd_date(format.parse(args.get("end_date")));
    }

    public void delete(Integer event_id) {
        CardEventEntity event = em.find(CardEventEntity.class, event_id);
        em.remove(event);
    }
}
