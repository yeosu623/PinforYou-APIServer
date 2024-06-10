package YU.PinforYouAPIServer.Repository.UserRepositoryComplex;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.ItemListEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ItemListRepository {

    ObjectMapper mapper = new ObjectMapper();

    @PersistenceContext
    EntityManager em;

    public ArrayList<ItemListEntity> getItemList(Integer user_id) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        if(user.getItem_list().isEmpty()) return new ArrayList<>();
        return mapper.readValue(user.getItem_list(), new TypeReference<>() {});
    }

    public void setItemList(Integer user_id, ArrayList<Integer> itemList) throws JsonProcessingException {
        UserEntity user = em.find(UserEntity.class, user_id);
        user.setItem_list(mapper.writeValueAsString(itemList));
    }
}
