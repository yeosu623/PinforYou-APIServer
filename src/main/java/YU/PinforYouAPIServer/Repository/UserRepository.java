package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    // 객체를 가져오는 부분은 Repository

    @PersistenceContext
    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from user u", User.class)
                 .getResultList();
    }
}
