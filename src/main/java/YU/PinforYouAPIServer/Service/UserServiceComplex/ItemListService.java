package YU.PinforYouAPIServer.Service.UserServiceComplex;

import YU.PinforYouAPIServer.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemListService {

    @Autowired
    UserRepository userRepository;
}
