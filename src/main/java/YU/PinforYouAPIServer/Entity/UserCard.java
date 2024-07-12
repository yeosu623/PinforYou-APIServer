package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_card")
@Getter @Setter
@NoArgsConstructor
public class UserCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_card_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private String card_num;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getUser_cards().add(this);
    }

    public void setCard(Card card) {
        this.card = card;
        card.setUser_card(this);
    }
}
