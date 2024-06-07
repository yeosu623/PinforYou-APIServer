package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "fellowship")
@Getter @Setter
@NoArgsConstructor
public class FellowshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer group_id;

    @Column
    private String group_name;

    @Column
    private Integer card_id;

    @Column
    private String category;

    @Column
    private String invite_code;
}
