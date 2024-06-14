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

    public List<UserCardEntity> card_recommend_by_store(Integer user_id, String category, String store_name) {
        CardBenefitAlgorithm algorithm = new CardBenefitAlgorithm();

        List<Integer> cards_id = new ArrayList<>();
        List<UserCardEntity> cards = userCardRepository.findByUserId(user_id);
        for(UserCardEntity card : cards) {
            cards_id.add(card.getCard_id());
        }

        List<UserCardEntity> selected_cards = new ArrayList<>();
        List<Integer> selected_cards_id = algorithm.calculate(cards_id);
        for(Integer selected_card_id : selected_cards_id) {
            selected_cards.add(userCardRepository.findByUserAndCardId(user_id, selected_card_id));
        }

        return selected_cards;
    }
}
