package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "challenge")
@Getter @Setter
@NoArgsConstructor
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challenge_id;

    @Column
    private String challenge_name;

    @Column
    private String challenge_info;

    @Column
    private Integer point;

    @Column
    private String category;

    @Column
    private Integer achieved_amount;
}
