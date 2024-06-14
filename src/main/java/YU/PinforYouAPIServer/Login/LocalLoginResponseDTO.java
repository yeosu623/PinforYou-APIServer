package YU.PinforYouAPIServer.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalLoginResponseDTO {

    private String userId;

    public LocalLoginResponseDTO(String userId) {
        this.userId = userId;
    }
}