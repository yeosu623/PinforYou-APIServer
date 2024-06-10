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
    private String username;

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
    // "String" in MySQL <-> "JSON" in JAVA

    /*
    "friend_id" : [1,2,3,4]
    */
    @Column
    private String friend;

    /*
    "challenge" : [
        {"challenge_id" : 1, "progress" : 3, "goal" : 5, "achieved" : false}
        {"challenge_id" : 2, "progress" : 6, "goal" : 10, "achieved" : false}
    ]
     */
    @Column
    private String challenge_progress;

    /*
    "item_list" : [
        {"item_id" : 1, "barcode" : "1234-5678-9011"},
        {"item_id" : 2, "barcode" : "0000-0000-1111"}
    ]
     */
    @Column
    private String item_list;

    /*
    "friend_request" : [1,2,3,4]
    */
    @Column
    private String friend_request;

    /*
    "group_request" : [1,2,3,4]
    */
    @Column
    private String group_request;
}
