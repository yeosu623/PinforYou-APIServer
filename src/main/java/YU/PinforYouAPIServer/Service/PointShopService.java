package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.PointShop;
import YU.PinforYouAPIServer.Repository.PointShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointShopService {

    @Autowired
    private PointShopRepository pointShopRepository;

    // 전체 아이템 목록 가져오기
    public List<PointShop> getAllItems() {
        return pointShopRepository.findAll();
    }

    // 특정 아이템 가져오기
    public PointShop getItemById(Long id) {
        return pointShopRepository.findById(id);
    }
}
