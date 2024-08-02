package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Other.LongListConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;
    private String username;
    private String password;
    private String tel;
    private String sex;
    private Long age;

    @Enumerated(EnumType.STRING)
    private PaymentCategory interest;

    private Long point;

    // "friend_id" : [1,2,3,4]
    @Convert(converter = LongListConverter.class)
    private List<Long> friend_ids = new ArrayList<>();

    // "friend_request" : [1,2,3,4]
    @Convert(converter = LongListConverter.class)
    private List<Long> friend_request = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fellowship_id")
    private Fellowship fellowship;

    // "group_request" : [1,2,3,4]
    @Convert(converter = LongListConverter.class)
    private List<Long> fellowship_request = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonManagedReference                                                   //StackOverFlow 방지용
    private List<ChallengeProgress> progresses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ItemList> item_lists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserCard> user_cards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<PaymentHistory> payment_histories = new ArrayList<>();

    @OneToMany(mappedBy = "leader")
    private List<FellowshipPaymentHistory> fellowship_payment_histories = new ArrayList<>();

    //==연관관계 메서드==//
    public void setFellowship(Fellowship fellowship) {
        this.fellowship = fellowship;
        fellowship.getUsers().add(this);
    }
}
