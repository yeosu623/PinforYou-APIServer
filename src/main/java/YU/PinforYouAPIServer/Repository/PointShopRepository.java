package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.PointShopEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PointShopRepository {

    @PersistenceContext
    EntityManager em;

    public PointShopEntity get(Integer item_id) {
        return em.find(PointShopEntity.class, item_id);
    }

    public void post(PointShopEntity PointShopEntity) {
        em.persist(PointShopEntity);
    }

    public void put(Integer item_id, Map<String, String> args) {
        // args : {"바꿀 컬럼 이름" : "바꿀 값"}
        PointShopEntity item = em.find(PointShopEntity.class, item_id);

        if(args.get("item_name") != null) item.setItem_name(args.get("item_name"));
        if(args.get("item_price") != null) item.setItem_price(Integer.parseInt("item_price"));
        if(args.get("category") != null) item.setCategory(args.get("category"));
    }

    public void delete(Integer item_id) {
        PointShopEntity item = em.find(PointShopEntity.class, item_id);
        em.remove(item);
    }
}
