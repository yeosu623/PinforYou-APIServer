package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "fellowship")
@Getter @Setter
@NoArgsConstructor
public class Fellowship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fellowship_id")
    private Long id;

    private String fellowship_name;

    @OneToMany(mappedBy = "fellowship")
    private List<User> users = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Enumerated(EnumType.STRING)
    private PaymentCategory category;

    private String invite_code;

    //==연관관계 메서드==//
    public void setCard(Card card) {
        this.card = card;
        card.getFellowships().add(this);
    }
}
