package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ItemListRepository {

    @PersistenceContext
    EntityManager em;

    /*
    public void save(ItemList itemList) {
        em.persist(itemList);
    }

    public ItemList findOne(Long item_list_id) {
        return em.find(ItemList.class, item_list_id);
    }
    */

    public boolean deleteOne(Long item_list_id) {
        ItemList itemList = em.find(ItemList.class, item_list_id);
        if (itemList != null){
            em.remove(itemList);
            return true;
        }
        else return false;
    }
}