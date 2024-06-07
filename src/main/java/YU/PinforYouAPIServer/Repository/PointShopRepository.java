package YU.PinforYouAPIServer.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class PointShopRepository {

    @PersistenceContext
    EntityManager em;
}
