package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    // friend, challenge_progress, item_list, friend_request, group_request
    // "String" in MySQL <-> "Array" in JAVA
    @Column
    private String friend; // 1D String Array; friend_id;

    @Column
    private String challenge_progress; // 2D String Array; challenge_id, progress, goal, achieved;

    @Column
    private String item_list; // 2D String Array; item_id, barcode;

    @Column
    private String friend_request; // 1D String Array; friend_id;

    @Column
    private String group_request; // 1D String Array; group_id;
}
