package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Repository.FellowshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FellowshipService {

    @Autowired
    private FellowshipRepository fellowshipRepository;

    // 모든 Fellowship의 특정 필드만 가져오기
    public List<Map<String, Object>> getAllFellowshipsWithSelectedFields() {
        List<Object[]> results = fellowshipRepository.findAllFellowshipsWithSelectedFields();
        return results.stream()
                .map(row -> Map.of(
                        "id", row[0],
                        "fellowshipName", row[1],
                        "cardId", row[2],
                        "category", row[3],
                        "inviteCode", row[4]
                ))
                .collect(Collectors.toList());
    }

    // 사용자 ID로부터 관련 Fellowship과 모든 User 정보 가져오기
    public List<Object[]> getFellowshipDetailsByUserId(Long userId) {
        return fellowshipRepository.findFellowshipDetailsByUserId(userId);
    }

    @Transactional
    public void removeUserFromFellowship(Long fellowship_id, Long user_id) {
        fellowshipRepository.removeUserFromFellowship(fellowship_id, user_id);
    }

    public void requestFellowship(Long user_id, Long fellowship_id) {
        fellowshipRepository.addUserToFellowshipRequest(user_id, fellowship_id);
    }
}
