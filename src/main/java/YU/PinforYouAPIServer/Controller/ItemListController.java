package YU.PinforYouAPIServer.Controller;

import YU.PinforYouAPIServer.Entity.ItemList;
import YU.PinforYouAPIServer.Entity.PointShop;
import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemListController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @GetMapping("/itemList")
    @ResponseBody
    public ResponseEntity<String> showItemList(@RequestParam("user_id") Long user_id) throws JsonProcessingException {
        List<ItemList> itemList = userRepository.findOne(user_id).getItem_lists();

        /* JSON 포맷
        {
            "user_id": 3,
            "item_lists": [
                {
                    "item_list_id": 1,
                    "item_id": 1,
                    "name": "스타벅스 아메리카노(G)",
                    "image_url": "http://...",
                    "category": "카폐"
                    "barcode": "1234-5678-0900-1111"
                },
                {
                    "item_list_id": 2,
                    "item_id": 1,
                    "name": "스타벅스 아메리카노(G)",
                    "image_url": "http://...",
                    "category": "카폐"
                    "barcode": "1999-9999-9999-0000"
                }
            ]
         */

        Map<String, Object> map1 = new LinkedHashMap<>();
        Map<String, Object> map2 = new LinkedHashMap<>();
        List<Object> list1 = new ArrayList<>();

        map1.put("user_id", user_id);
        map1.put("item_list", list1);

        for(ItemList item : itemList) {
            PointShop product = item.getProduct();
            map2 = new LinkedHashMap<>();

            map2.put("item_list_id", item.getId());
            map2.put("item_id", product.getId());
            map2.put("name", product.getItem_name());
            map2.put("image_url", product.getImage_url());
            map2.put("category", product.getCategory());
            map2.put("barcode", item.getBarcode());

            list1.add(map2);
        }

        String jsonStr = mapper.writeValueAsString(map1);
        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }
}
