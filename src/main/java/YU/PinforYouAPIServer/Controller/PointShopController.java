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
import YU.PinforYouAPIServer.Entity.ItemList;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.ItemListRepository;
import YU.PinforYouAPIServer.Repository.PointShopRepository;
import YU.PinforYouAPIServer.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import YU.PinforYouAPIServer.Entity.PointShop;
import YU.PinforYouAPIServer.Service.PointShopService;
import YU.PinforYouAPIServer.Category.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PointShopController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private PointShopService pointShopService;

    @Autowired
    PointShopRepository pointShopRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemListRepository itemListRepository;

    // 사용자가 포인트로 물건 구매가 가능한지 확인
    @GetMapping("/pointShop/purchaseItem")
    @ResponseBody
    public ResponseEntity<String> purchaseItme(@RequestParam("item_id") Long item_id,
                                               @RequestParam("user_id") Long user_id) throws JsonProcessingException {
        PointShop itemInfo = pointShopRepository.findByPointId(item_id);
        User userInfo = userRepository.findOne(user_id);

        Long user_point = userInfo.getPoint();
        Long item_point = itemInfo.getItem_price();

        // 포인트로 결제가 가능한 경우
        if(item_point <= user_point){
            Map<String, Object> result = new HashMap<>();
            result.put("result", "true");

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        } else {    // 포인트로 결제가 불가능한 경우
            Map<String, Object> result = new HashMap<>();
            result.put("result", "false");

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        }
    }

    // 사용자의 구매가 결정되면 아이템의 바코드를 발행
    @GetMapping("/pointShop/purchaseItem/makeBarcode")
    @ResponseBody
    public ResponseEntity<String> purchaseItmeWithBarcode(@RequestParam("item_id") Long item_id,
                                                          @RequestParam("user_id") Long user_id,
                                                          @RequestParam("purchase") int purchase) throws JsonProcessingException {
        PointShop itemInfo = pointShopRepository.findByPointId(item_id);
        User userInfo = userRepository.findOne(user_id);

        // 구매함
        if (purchase == 1) {
            ItemList itemList = new ItemList();
            itemList.setUser(userInfo);
            itemList.setItem(itemInfo);
            // itemListRepository.save(itemList);
            // 바코드 생성후 저장
            itemListRepository.saveBarcode(itemList);

            // user의 point 차감
            Long userPoint = userInfo.getPoint() - itemInfo.getItem_price();
            userInfo.setPoint(userPoint);
            userRepository.save(userInfo);

            Map<String, Object> result = new HashMap<>();
            result.put("success", 1);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        } else {    // 구매하지 않음
            Map<String, Object> result = new HashMap<>();
            result.put("success", 0);

            String jsonStr = mapper.writeValueAsString(result);
            return new ResponseEntity<>(jsonStr, HttpStatus.OK);
        }
    }

    // 전체 아이템 목록 가져오기
    @GetMapping("/pointShop/items")
    public ResponseEntity<List<PointShop>> getAllItems() {
        List<PointShop> items = pointShopService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // 특정 카테고리로 아이템 목록 가져오기
    @GetMapping("/pointShop/items/category")
    public ResponseEntity<List<PointShop>> getItemsByCategory(@RequestParam("category") ItemCategory category) {
        List<PointShop> items = pointShopService.getItemsByCategory(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
