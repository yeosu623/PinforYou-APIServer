package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.Fellowship;
import YU.PinforYouAPIServer.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FellowshipRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Fellowship fellowship) {
        em.persist(fellowship);
    }

    public Fellowship find(Long fellowship_id) {
        return em.find(Fellowship.class, fellowship_id);
    }

    // 모든 Fellowship의 특정 필드만 가져오기
    public List<Object[]> findAllFellowshipsWithSelectedFields() {
        String query = "SELECT f.id, f.fellowship_name, f.card.id, f.category, f.invite_code " + "FROM fellowship f";
        TypedQuery<Object[]> typedQuery = em.createQuery(query, Object[].class);
        return typedQuery.getResultList();
    }

    // 사용자 ID로부터 관련 Fellowship과 해당 Fellowship의 모든 User 정보 가져오기
    public List<Object[]> findFellowshipDetailsByUserId(Long userId) {
        TypedQuery<Object[]> query = em.createQuery(
                        "SELECT f.fellowship_name, f.card.id, f.category, f.invite_code, " +
                                "u.id, u.nickname, u.username " +
                                "FROM fellowship f JOIN f.users u WHERE f.id = " +
                                "(SELECT u2.fellowship.id FROM user u2 WHERE u2.id = :userId)", Object[].class)
                .setParameter("userId", userId);

        return query.getResultList();
    }

    // fellowship_id 찾아서 데이터 전달
    public Fellowship findByFellowshipId(Long fellowship_id) {
        return em.find(Fellowship.class, fellowship_id);
    }

    // fellowship_id에 해당하는 User 리스트
    public List<User> findFellowshipUsersByFellowshipId(Long fellowship_id) {
        String query = "SELECT u FROM user u WHERE u.fellowship.id = :fellowship_id";
        TypedQuery<User> typedQuery = em.createQuery(query, User.class);
        typedQuery.setParameter("fellowship_id", fellowship_id);
        return typedQuery.getResultList();
    }
}
