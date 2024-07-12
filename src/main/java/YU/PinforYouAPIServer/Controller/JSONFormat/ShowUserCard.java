package YU.PinforYouAPIServer.Controller.JSONFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ShowUserCard {
    public Long card_id;
    public String card_name;
    public String card_num;
}
