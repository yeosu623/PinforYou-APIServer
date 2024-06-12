package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.UserCardEntity;
import YU.PinforYouAPIServer.Other.CardBenefitAlgorithm;
import YU.PinforYouAPIServer.Repository.UserCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserCardService {

    @Autowired
    UserCardRepository userCardRepository;

    public void card_recommend_by_store(Integer user_id, String category, String store_name) {
        CardBenefitAlgorithm algorithm = new CardBenefitAlgorithm();

        List<UserCardEntity> user_cards = userCardRepository.findByUserId(user_id);

    }
}
