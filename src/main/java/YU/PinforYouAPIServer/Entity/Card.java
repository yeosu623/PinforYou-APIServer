package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "card")
@Getter @Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private String card_name;
    private String company_code;
    private String card_color;
    private String card_code;
    private String company;
    private String image_url;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private CardEvent card_event;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private PayEvent pay_event;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private UserCard user_card;

    @OneToMany(mappedBy = "card")
    private List<Fellowship> fellowships;

    @OneToMany(mappedBy = "card")
    private List<PaymentHistory> payment_histories;
}
