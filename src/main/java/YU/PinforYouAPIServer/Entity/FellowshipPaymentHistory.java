package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "fellowship_payment_history")
@Getter @Setter
@NoArgsConstructor
public class FellowshipPaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fellowship_payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fellowship_id")
    private Fellowship fellowship;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private User leader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private Long pay_amount;
    private LocalDateTime purchase_date;
    private String store_name;

    @Enumerated(EnumType.STRING)
    private PaymentCategory category;

    //==연관관계 메서드==//
    public void setLeader(User leader) {
        this.leader = leader;
        leader.getFellowship_payment_histories().add(this);
    }

    public void setCard(Card card) {
        this.card = card;
        card.getFellowship_payment_histories().add(this);
    }

    public void setFellowship(Fellowship fellowship) {
        this.fellowship = fellowship;
        fellowship.getFellowship_payment_histories().add(this);
    }
}
