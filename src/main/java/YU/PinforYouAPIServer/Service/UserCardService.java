package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Algorithm.NewCardRecommendAlgorithm;
import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.PaymentHistory;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCardService {

    @Autowired
    NewCardRecommendAlgorithm newCardRecommendAlgorithm;

    public Card newCardRecommend(Long user_id) {
        return newCardRecommendAlgorithm.calculateByUser(user_id);
    }
}
