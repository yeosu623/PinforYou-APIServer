package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "group_request")
@Getter @Setter
@NoArgsConstructor
public class GroupRequestEntity {

    @Id
    private Integer group_id;

    @Column
    private Integer user_id;
}
