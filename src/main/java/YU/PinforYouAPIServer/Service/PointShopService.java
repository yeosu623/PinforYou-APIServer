package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.PointShop;
import YU.PinforYouAPIServer.Repository.PointShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import YU.PinforYouAPIServer.Category.ItemCategory;

@Service
public class PointShopService {

    @Autowired
    private PointShopRepository pointShopRepository;

    // 전체 아이템 목록 가져오기
    public List<PointShop> getAllItems() {
        return pointShopRepository.findAll();
    }

    // 특정 카테고리로 아이템 목록 가져오기
    public List<PointShop> getItemsByCategory(ItemCategory category) {
        return pointShopRepository.findByCategory(category);
    }
}
