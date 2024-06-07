package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "card_event")
@Getter @Setter
@NoArgsConstructor
public class CardEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer event_id;

    @Column
    private Integer card_id;

    @Column
    private String banner_image_url;

    @Column
    private String content_image_url;

    @Column
    private Date start_date;

    @Column
    private Date end_date;
}
