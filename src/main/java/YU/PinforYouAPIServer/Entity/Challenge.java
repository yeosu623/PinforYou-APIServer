package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "challenge")
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   //StackOverFlow 방지용
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long id;

    @Column(name = "challenge_name")
    private String name;

    @Column(name = "challenge_info")
    private String info;

    @OneToMany(mappedBy = "challenge")
    @JsonManagedReference                                       //StackOverFlow 방지용
    private List<ChallengeProgress> progresses;

    private Long point;

    @Enumerated(EnumType.STRING)
    private PaymentCategory category;

    private Long cost;
    private Long goal;
}
