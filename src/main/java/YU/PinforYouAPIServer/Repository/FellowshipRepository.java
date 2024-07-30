package YU.PinforYouAPIServer.Repository;

import YU.PinforYouAPIServer.Entity.Fellowship;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import YU.PinforYouAPIServer.Entity.User;

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

    // 사용자 ID와 모임 ID를 통해 fellowship_id 값을 제거
    public void removeUserFromFellowship(Long fellowship_id, Long user_id) {
        em.createQuery("UPDATE user u SET u.fellowship = null WHERE u.id = :user_id AND u.fellowship.id = :fellowship_id")
                .setParameter("user_id", user_id)
                .setParameter("fellowship_id", fellowship_id)
                .executeUpdate();
    }

    public void addUserToFellowshipRequest(Long user_id, Long fellowship_id) {
        // 1. fellowshipId의 leader_id 값을 가져옴
        TypedQuery<Fellowship> fellowshipQuery = em.createQuery("SELECT f FROM fellowship f WHERE f.id = :fellowship_id", Fellowship.class);
        fellowshipQuery.setParameter("fellowship_id", fellowship_id);
        Fellowship fellowship = fellowshipQuery.getSingleResult();
        Long leader_id = fellowship.getLeader_id();

        // 2. userId의 friend_ids 안에 leaderId가 있는지 확인
        TypedQuery<User> userQuery = em.createQuery("SELECT u FROM user u WHERE u.id = :user_id", User.class);
        userQuery.setParameter("user_id", user_id);
        User user = userQuery.getSingleResult();

        if (user.getFriend_ids().contains(leader_id)) {
            // 3. leaderId의 user를 찾아서 fellowship_request에 userId를 추가
            TypedQuery<User> leaderQuery = em.createQuery("SELECT u FROM user u WHERE u.id = :leader_id", User.class);
            leaderQuery.setParameter("leader_id", leader_id);
            User leader = leaderQuery.getSingleResult();

            List<Long> fellowshipRequest = leader.getFellowship_request();
            if (!fellowshipRequest.contains(user_id)) {
                fellowshipRequest.add(user_id);
                leader.setFellowship_request(fellowshipRequest);
                em.merge(leader); // User 엔티티의 fellowship_request를 DB에 반영
            }
        }
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