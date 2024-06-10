package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_card")
@Getter @Setter
@NoArgsConstructor
public class UserCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_card_id;

    @Column
    private Integer user_id;

    @Column
    private Integer card_id;

    @Column
    private String card_num;
}
