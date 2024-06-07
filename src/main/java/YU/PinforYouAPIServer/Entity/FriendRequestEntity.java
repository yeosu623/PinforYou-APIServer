package YU.PinforYouAPIServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "friend_request")
@Getter @Setter
@NoArgsConstructor
public class FriendRequestEntity {

    @Id
    private Integer user_id;

    @Column
    private Integer friend_id;
}
