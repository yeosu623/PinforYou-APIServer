package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.FellowshipEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class FellowshipRepository {

    @PersistenceContext
    EntityManager em;

    public FellowshipEntity get(Integer group_id) {
        return em.find(FellowshipEntity.class, group_id);
    }

    public void post(FellowshipEntity FellowshipEntity) {
        em.persist(FellowshipEntity);
    }

    public void put(Integer group_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        FellowshipEntity group = em.find(FellowshipEntity.class, group_id);

        if(args.get("group_name") != null) group.setGroup_name(args.get("group_name"));
        if(args.get("card_id") != null) group.setCard_id(Integer.parseInt(args.get("card_id")));
        if(args.get("category") != null) group.setCategory(args.get("category"));
        if(args.get("invite_code") != null) group.setInvite_code(args.get("invite_code"));
    }

    public void delete(Integer group_id) {
        FellowshipEntity group = em.find(FellowshipEntity.class, group_id);
        em.remove(group);
    }
}
