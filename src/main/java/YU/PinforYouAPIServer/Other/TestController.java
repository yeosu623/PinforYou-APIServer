package YU.PinforYouAPIServer.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    ImageService imageService;
    @GetMapping("/getImageUrl")
    @ResponseBody
    public ResponseEntity<String> getImageUrl() {
        String imageUrl = imageService.getUrl("pointShop/스타벅스 아메리카노.jpg");

        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
}
