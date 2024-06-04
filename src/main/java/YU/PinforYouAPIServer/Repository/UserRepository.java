package YU.PinforYouAPIServer.Repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class UserRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column private String id;
    @Column private String pw;
    @Column private String tel;
    @Column private String sex;
    @Column private Integer age;
    @Column private String interest;
    @Column private Integer point;

    //JSON friend
    //JSON challengeProgress
    //JSON itmelist
}
