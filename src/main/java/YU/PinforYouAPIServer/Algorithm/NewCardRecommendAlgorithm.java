package YU.PinforYouAPIServer.Algorithm;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Entity.PaymentHistory;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.CardRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Transactional
public class NewCardRecommendAlgorithm {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    public Card calculate(Long user_id) {
        User user = userRepository.findOne(user_id);

        // 유저의 결제 내역 중, 각각의 카테고리가 얼마나 나왔는지 세기
        PaymentCategory[] categoryList = PaymentCategory.values();
        int[] count = new int[categoryList.length];
        for(PaymentHistory history : user.getPayment_histories())
            count[history.getCategory().ordinal()]++;

        // 센 것에서, 가장 카테고리가 많이 나온 인덱스를 반환 (최댓값이 2개 이상일 경우, 가장 왼쪽의 것을 반환)
        int max = Arrays.stream(count).max().getAsInt();
        int index = Arrays.stream(count).boxed().mapToInt(Integer::intValue).boxed().toList().indexOf(max);

        // 인덱스를 카테고리 이름으로 변경
        PaymentCategory category = categoryList[index];

        // 카테고리에 해당하는 카드 중, 랜덤으로 하나를 추천
        List<Card> cards = cardRepository.findByCategory(category);
        Random rd = new Random();
        int rdInt = rd.nextInt(cards.size());

        return cards.get(rdInt);
    }
}
