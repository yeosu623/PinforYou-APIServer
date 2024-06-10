package YU.PinforYouAPIServer.Service.UserServiceComplex;

import YU.PinforYouAPIServer.Repository.UserRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRepository;
import YU.PinforYouAPIServer.Repository.UserRepositoryComplex.FriendRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendRequestService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    FriendService friendService;

    public void add_friend_request(Integer user_id, Integer friend_id) throws JsonProcessingException {
        ArrayList<Integer> friend_requests = friendRequestRepository.getFriendRequest(user_id);
        friend_requests.add(friend_id);
        friendRequestRepository.setFriendRequest(user_id, friend_requests);
    }

    public void accept_friend_request(Integer user_id, Integer friend_id) throws JsonProcessingException {
        ArrayList<Integer> friend_requests = friendRequestRepository.getFriendRequest(user_id);
        friend_requests.remove(friend_id);
        friendService.add_friend(user_id, friend_id);
        friendRequestRepository.setFriendRequest(user_id, friend_requests);
    }

    public void remove_friend_request(Integer user_id, Integer friend_id) throws JsonProcessingException {
        ArrayList<Integer> friend_requests = friendRequestRepository.getFriendRequest(user_id);
        friend_requests.remove(friend_id);
        friendRequestRepository.setFriendRequest(user_id, friend_requests);
    }
}
