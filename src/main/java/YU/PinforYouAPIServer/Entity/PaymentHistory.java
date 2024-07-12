package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "payment_history")
@Getter @Setter
@NoArgsConstructor
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private Long pay_amount;
    private LocalDateTime purchase_date;
    private String store_name;

    @Enumerated(EnumType.STRING)
    private PaymentCategory category;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getPayment_histories().add(this);
    }

    public void setCard(Card card) {
        this.card = card;
        card.getPayment_histories().add(this);
    }
}
