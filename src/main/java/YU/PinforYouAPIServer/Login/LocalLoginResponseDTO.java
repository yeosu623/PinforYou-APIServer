package YU.PinforYouAPIServer.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalLoginResponseDTO {

    private Integer user_id;

    public LocalLoginResponseDTO(Integer user_id) {
        this.user_id = user_id;
    }
}