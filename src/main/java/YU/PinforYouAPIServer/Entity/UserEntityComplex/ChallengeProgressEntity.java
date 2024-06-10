package YU.PinforYouAPIServer.Entity.UserEntityComplex;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeProgressEntity {
    private Integer user_id;
    private Integer challenge_id;
    private Integer progress;
    private Integer goal;
    private boolean achieved;
}
