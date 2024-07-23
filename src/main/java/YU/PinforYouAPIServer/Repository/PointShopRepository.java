package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.ItemList;
import YU.PinforYouAPIServer.Entity.PointShop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class PointShopRepository {

    @PersistenceContext
    EntityManager em;

    /*// 전체 목록 불러옴
    public List<Challenge> findAll() {
        return em.createQuery("select c from challenge c", Challenge.class)
                .getResultList();
    }

    // 하나만 받아옴
    public Challenge findByChallengeId(Long challenge_id){
        return em.createQuery("select c from challenge c where c.id= :challenge_id", Challenge.class)
                .setParameter("challenge_id", challenge_id)
                .getSingleResult();
    }*/

    public PointShop findByPointId(Long item_id){
        return em.find(PointShop.class, item_id);
    }

    // 데이터 삽입
    public ItemList insert(ItemList item) {
        em.persist(item);
        return item;
    }
}