package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Getter @Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column
    private String id;

    @Column
    private String pw;

    @Column
    private String tel;

    @Column
    private String sex;

    @Column
    private Integer age;

    @Column
    private String interest;

    @Column
    private Integer point;

    // friend, challenge_progress, item_list 는 JSON 타입으로 되어있다.
    @Column
    private Object friend;

    @Column
    private Object challenge_progress;

    @Column
    private Object item_list;

}
