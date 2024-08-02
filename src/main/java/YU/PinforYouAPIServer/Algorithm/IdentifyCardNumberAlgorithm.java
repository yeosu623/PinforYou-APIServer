package YU.PinforYouAPIServer.Algorithm;


import YU.PinforYouAPIServer.Entity.Card;
import YU.PinforYouAPIServer.Repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class IdentifyCardNumberAlgorithm {
    /* 카드의 16자리(1234567890123456)를 받으면,
    첫 4자리로 카드의 회사를 구분하고
    그 다음 4자리로 그 회사의 어떤 카드인지 구분하는 알고리즘.

    구분 범위는 "카드 혜택.xlsx"를 참조할 것 */

    @Autowired
    CardRepository cardRepository;

    public Card calculate(String number) {
        int first4 = Integer.parseInt(number.substring(0, 4));
        int next4 = Integer.parseInt(number.substring(4, 8));
        long card_id = -1;

        if(first4 < 2500) {
            if(next4 < 2500)       card_id = 1;
            else if(next4 < 5000)  card_id = 2;
            else if(next4 < 7500)  card_id = 3;
            else if(next4 < 10000) card_id = 4;
        }
        else if(first4 < 5000) {
            if(next4 < 2500)       card_id = 5;
            else if(next4 < 5000)  card_id = 6;
            else if(next4 < 7500)  card_id = 7;
            else if(next4 < 10000) card_id = 8;
        }
        else if(first4 < 7500) {
            if(next4 < 2500)       card_id = 9;
            else if(next4 < 5000)  card_id = 10;
            else if(next4 < 7500)  card_id = 11;
            else if(next4 < 10000) card_id = 12;
        }
        else if(first4 < 10000) {
            if(next4 < 2500)       card_id = 13;
            else if(next4 < 5000)  card_id = 14;
            else if(next4 < 7500)  card_id = 15;
            else if(next4 < 10000) card_id = 16;
        }

        return cardRepository.find(card_id);
    }
}
