package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "pay_event")
@Getter @Setter
@NoArgsConstructor
public class PayEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private String banner_image_url;
    private String content_image_url;
    private LocalDateTime start_date;
    private LocalDateTime end_date;

    //==연관관계 메서드==//
    public void setCard(Card card) {
        this.card = card;
        card.setPay_event(this);
    }
}
