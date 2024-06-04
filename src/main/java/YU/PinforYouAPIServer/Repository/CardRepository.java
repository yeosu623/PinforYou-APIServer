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
@Table(name = "Card")
public class CardRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column private String cardName;
    @Column private String companyCode;
    @Column private String cardColor; // HEX로 저장. (예 : 0FF0FE)
    @Column private String cardCode;
    @Column private String company;

}
