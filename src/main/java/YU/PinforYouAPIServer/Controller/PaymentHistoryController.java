package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Service.PaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHistoryController {

    @Autowired
    PaymentHistoryService paymentHistoryService;
}
