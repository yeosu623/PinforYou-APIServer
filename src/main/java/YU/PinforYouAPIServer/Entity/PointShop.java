package YU.PinforYouAPIServer.Entity;

import YU.PinforYouAPIServer.Category.ItemCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    private String image_url;

    @JsonIgnore                             // ItemList와 @OneToMany 관계때문에, PointShop 엔티티를 JSON으로 변환할 때 연관된 ItemList 엔티티의 User 정보까지 함께 포함하는 문제 발생
    @OneToMany(mappedBy = "product")
    private List<ItemList> product_lists;
}
