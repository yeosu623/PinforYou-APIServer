package YU.PinforYouAPIServer.Service.UserServiceComplex;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRepository friendRepository;

    public void add_friend(Integer user_id, Integer friend_id) throws JsonProcessingException {
        ArrayList<Integer> friends = friendRepository.getFriend(user_id);
        friends.add(friend_id);
        friendRepository.setFriend(user_id, friends);
    }

    public void remove_friend(Integer user_id, Integer friend_id) throws JsonProcessingException {
        ArrayList<Integer> friends = friendRepository.getFriend(user_id);
        friends.remove(friend_id);
        friendRepository.setFriend(user_id, friends);
    }
}
