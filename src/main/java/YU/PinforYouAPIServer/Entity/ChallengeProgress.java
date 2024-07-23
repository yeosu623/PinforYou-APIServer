package YU.PinforYouAPIServer.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "challenge_progress")
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})      //StackOverFlow 방지용
public class ChallengeProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_progress_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    @JsonBackReference                       //StackOverFlow 방지용
    private Challenge challenge;

    private Long progress;

    @Column(name = "achieved")
    private Boolean achieved;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getProgresses().add(this);
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
        challenge.getProgresses().add(this);
    }
}
