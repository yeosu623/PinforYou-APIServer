package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "item_list")
@Getter @Setter
@NoArgsConstructor
public class ItemList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_list_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private PointShop item;

    private String barcode;

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.getItem_lists().add(this);
    }

    public void setItem(PointShop item) {
        this.item = item;
        item.getItem_lists().add(this);
    }
}
