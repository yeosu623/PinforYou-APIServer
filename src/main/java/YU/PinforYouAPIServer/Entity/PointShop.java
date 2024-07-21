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
    private String use_place;
    private Long item_price;

    @Enumerated
    private ItemCategory category;

    private String image_url;

    @OneToMany(mappedBy = "product")
    private List<ItemList> product_lists;
}
