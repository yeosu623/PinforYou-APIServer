package YU.PinforYouAPIServer.Service;

import org.springframework.stereotype.Service;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findOne(userId);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    public boolean acceptFellowshipRequest(Long userId, List<Long> acceptedRequests) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return false;
        }

        List<Long> fellowshipRequest = user.getFellowship_request();
        for (Long acceptedId : acceptedRequests) {
            if (fellowshipRequest.contains(acceptedId)) {
                User acceptedUser = userRepository.findOne(acceptedId);
                if (acceptedUser != null) {
                    acceptedUser.setFellowship(user.getFellowship());
                    userRepository.update(acceptedUser);
                }
                fellowshipRequest.remove(acceptedId);
            }
        }
        user.setFellowship_request(fellowshipRequest);
        userRepository.update(user);

        return true;
    }
}