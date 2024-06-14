package YU.PinforYouAPIServer.Other;

import java.util.ArrayList;
import java.util.List;

public class CardBenefitAlgorithm {

    public List<Integer> calculate(List<Integer> card_ids) {

        // 각각의 card_id에 따른 할인 값을 계산하고,
        // 가장 할인 값이 큰 순으로 3개를 ArrayList<Integer> results에 저장한다.
        // 만약 카드가 1개나 2개일 경우, 1개나 2개만 저장한다.

        List<Integer> result = new ArrayList<>();

        // 임시 알고리즘. 맨 앞의 3개만 가져오고 끝.
        for(int i = 0; i < card_ids.size(); i++) {
            result.add(card_ids.get(i));
            if(i == 2) break;
        }

        return result;
    }
}
