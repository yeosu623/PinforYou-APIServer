package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user_card")
@Getter @Setter
@NoArgsConstructor
public class UserCardEntity {

    @Id
    private Integer user_id;

    @Column
    private Integer card_id;

    @Column
    private String card_num;
}
