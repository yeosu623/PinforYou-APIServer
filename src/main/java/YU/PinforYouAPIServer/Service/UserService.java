package YU.PinforYouAPIServer.Service;

import org.springframework.stereotype.Service;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
}