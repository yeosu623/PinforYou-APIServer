package YU.PinforYouAPIServer.Entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter @Setter
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String id;

    @Column
    private String pw;

    @Column
    private String tel;

    @Column
    private String sex;

    @Column
    private int age;

    @Column
    private String interest;

    @Column
    private int point;

    // friend, challengeProgress, itemlist는 JSON 타입으로 되어있음.
    // 데이터베이스와 JPA 사이에 JSON을 다루는 방법은 많지만,
    // 여기서는 vladmihalcea가 만든 라이브러리(?)를 사용하려고 함.

    // 참고 링크 : https://balldev.tistory.com/79
    @Type(type = "json")
    @Column

}
