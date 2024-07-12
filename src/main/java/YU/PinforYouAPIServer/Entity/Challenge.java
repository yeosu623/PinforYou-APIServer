package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "challenge")
@Getter @Setter
@NoArgsConstructor
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    private String name;
    private String info;

    @OneToMany(mappedBy = "challenge")
    private List<ChallengeProgress> progresses;

    private Long point;

    @Enumerated(EnumType.STRING)
    private PaymentCategory category;

    private Long cost;
    private Long goal;
}
