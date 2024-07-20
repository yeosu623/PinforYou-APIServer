package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserCardService {

    @Autowired
    UserRepository userRepository;

    /*
    미완성 : 실제 알고리즘 제작 필요
     */
    public Card newCardAlgorithm(Long user_id) {
        return userRepository.findOne(user_id).getUser_cards().get(0).getCard();
    }
}
