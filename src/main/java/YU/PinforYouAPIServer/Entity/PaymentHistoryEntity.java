package YU.PinforYouAPIServer.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "payment_history")
@Getter @Setter
@NoArgsConstructor
public class PaymentHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payment_id;

    @Column
    private Integer user_id;

    @Column
    private Integer card_id;

    @Column
    private Integer pay_amount;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchase_date;

    @Column
    private String store_name;

    @Column
    private String category;
}
