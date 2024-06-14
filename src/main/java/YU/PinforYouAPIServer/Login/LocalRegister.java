package YU.PinforYouAPIServer.Login;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LocalRegister {

    private static final Logger logger = LoggerFactory.getLogger(LocalRegister.class);

    @Autowired
    private LoginService loginService;

    // 로컬 회원가입을 하여 User 테이블에 저장하기
    // 회원가입 정보 : 이메일(id), 비밀번호, 비밀번호 확인, 이름, 휴대폰 번호, 성별, 나이, 카테고리
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LocalRegisterDTO registerDto){

        Optional<UserEntity> userOptional = loginService.findById(registerDto.getId());

        if (userOptional.isPresent()){
            logger.warn("This id already Exist : {}", registerDto.getId());
            return ResponseEntity.status(409).body("Id already exist");
        }
        // User 정보 세팅
        UserEntity user = new UserEntity();
        user.setId(registerDto.getId());
        user.setPw(registerDto.getPw());
        user.setUsername(registerDto.getName());
        user.setTel(registerDto.getTel());
        user.setSex(registerDto.getSex());
        user.setAge(registerDto.getAge());
        user.setInterest(registerDto.getInterest());
        user.setPoint(0);
        user.setFriend("");
        user.setChallenge_progress("");
        user.setItem_list("");
        user.setFriend_request("");
        user.setGroup_request("");

        // 사용자 정보 저장
        loginService.save(user);
        logger.info("User registered successfully : {}", user.getId());
        return ResponseEntity.ok("User registered successfully");
    }
}