package YU.PinforYouAPIServer.Login;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LocalLogin {

    private static final Logger logger = LoggerFactory.getLogger(LocalLogin.class);

    @Autowired
    private LoginService loginService;

    // 로컬 로그인을 하여 UserId를 반환하기
    @PostMapping("/localLogin")
    public ResponseEntity<?> localLogin(@RequestBody LocalLoginDTO loginInfo){

        Optional<UserEntity> userOptional = loginService.findById(loginInfo.getId());

        if (userOptional.isPresent()){
            UserEntity user = userOptional.get();
            if (user.getPw().equals(loginInfo.getPw())) {
                logger.info("Login successful for user: {}", user.getId());
                return ResponseEntity.ok(new LocalLoginResponseDTO(user.getUser_id()));
            }
            else {
                logger.warn("Invalid password for user: {}", user.getId());
                return ResponseEntity.status(401).body("Mismatched password");
            }
        }
        else {
            // Id를 찾을 수 없는 경우
            return ResponseEntity.status(404).body("User not found");
        }
    }

}