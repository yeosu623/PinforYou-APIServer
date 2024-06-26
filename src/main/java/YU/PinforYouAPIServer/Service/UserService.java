package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.ChallengeEntity;
import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Entity.UserEntityComplex.ChallengeProgressEntity;
import YU.PinforYouAPIServer.Repository.ChallengeRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void change_username(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("username", value);

        userRepository.put(user_id, map);
    }

    public void change_id(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("id", value);

        userRepository.put(user_id, map);
    }

    public void change_pw(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("pw", value);

        userRepository.put(user_id, map);
    }

    public void change_tel(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("tel", value);

        userRepository.put(user_id, map);
    }

    public void change_sex(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("sex", value);

        userRepository.put(user_id, map);
    }

    public void change_age(Integer user_id, Integer value) {
        Map<String, String> map = new HashMap<>();
        map.put("age", value.toString());

        userRepository.put(user_id, map);
    }

    public void change_interest(Integer user_id, String value) {
        Map<String, String> map = new HashMap<>();
        map.put("interest", value);

        userRepository.put(user_id, map);
    }

    public void change_point(Integer user_id, Integer value) {
        Map<String, String> map = new HashMap<>();
        map.put("point", value.toString());

        userRepository.put(user_id, map);
    }
}
