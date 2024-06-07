package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "card")
@Getter @Setter
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer card_id;

    @Column
    private String card_name;

    @Column
    private String company_code;

    @Column
    private String card_color;

    @Column
    private String card_code;

    @Column
    private String company;
}
