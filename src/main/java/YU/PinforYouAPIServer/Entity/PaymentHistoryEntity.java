package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "payment_history")
@Getter @Setter
@NoArgsConstructor
public class PaymentHistoryEntity {

    @Id
    private Integer user_id;

    @Column
    private Integer card_id;

    @Column
    private Integer pay_amount;

    @Column
    private Date purchase_date;

    @Column
    private String store_name;

    @Column
    private String category;
}
