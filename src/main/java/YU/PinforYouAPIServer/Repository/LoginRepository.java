package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepository {

    // Id로 사용자 찾기
    @PersistenceContext
    EntityManager em;

    public Optional<UserEntity> findById(String id) {
        try {
            UserEntity user = em.createQuery("select u from user u where u.id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    public void save(UserEntity user) {
        em.persist(user);
    }
}