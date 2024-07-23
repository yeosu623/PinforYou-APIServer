package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.PointShop;
import YU.PinforYouAPIServer.Service.PointShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pointshop")
public class PointShopController {

    @Autowired
    private PointShopService pointShopService;

    // 전체 아이템 목록 가져오기
    @GetMapping("/items")
    public ResponseEntity<List<PointShop>> getAllItems() {
        List<PointShop> items = pointShopService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // 특정 아이템 가져오기
    @GetMapping("/items/{id}")
    public ResponseEntity<PointShop> getItemById(@PathVariable Long id) {
        PointShop item = pointShopService.getItemById(id);
        if (item != null) {
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
