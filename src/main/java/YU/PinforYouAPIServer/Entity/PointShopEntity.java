package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "point_shop")
@Getter @Setter
@NoArgsConstructor
public class PointShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;

    @Column
    private String item_name;

    @Column
    private Integer item_price;

    @Column
    private String category;
}
