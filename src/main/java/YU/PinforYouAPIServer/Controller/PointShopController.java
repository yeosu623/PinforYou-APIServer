package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.PointShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointShopController {

    @Autowired
    PointShopService pointShopService;
}
