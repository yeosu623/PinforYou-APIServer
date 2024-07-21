package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.PaymentCategory;
import YU.PinforYouAPIServer.Other.LongListConverter;
import YU.PinforYouAPIServer.Other.StringListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "card")
@Getter @Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    private String card_name;
    private String company_code;
    private String card_color;
    private String card_code;
    private String company;
    private String image_url;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private CardEvent card_event;

    @OneToOne(mappedBy = "card", fetch = FetchType.LAZY)
    private PayEvent pay_event;

    @OneToMany(mappedBy = "card")
    private List<UserCard> user_card;

    @OneToMany(mappedBy = "card")
    private List<Fellowship> fellowships;

    @OneToMany(mappedBy = "card")
    private List<PaymentHistory> payment_histories;

    @Enumerated(EnumType.STRING)
    private PaymentCategory major_benefit;

    // "benefits_description" : ["aaa", "bbb", "ccc"]
    @Convert(converter = StringListConverter.class)
    private List<String> benefits_description = new ArrayList<>();
}
