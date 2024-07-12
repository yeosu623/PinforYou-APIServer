package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "card_event")
@Getter @Setter
@NoArgsConstructor
public class CardEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private String banner_image_url;
    private String content_image_url;
    private Date start_date;
    private Date end_date;

    //==연관관계 메서드==//
    public void setCard(Card card) {
        this.card = card;
        card.setCard_event(this);
    }
}
