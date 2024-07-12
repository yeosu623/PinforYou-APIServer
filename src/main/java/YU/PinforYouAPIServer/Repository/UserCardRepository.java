package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCardRepository {

    @PersistenceContext
    EntityManager em;


}
