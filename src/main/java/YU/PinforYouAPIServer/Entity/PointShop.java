package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.ItemCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "point_shop")
@Getter @Setter
@NoArgsConstructor
public class PointShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String item_name;
    private Long item_price;

    @Enumerated
    private ItemCategory category;

    @OneToMany(mappedBy = "item")
    private List<ItemList> item_lists;
}
