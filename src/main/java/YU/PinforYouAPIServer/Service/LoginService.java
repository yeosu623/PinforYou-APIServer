package YU.PinforYouAPIServer.Service;

import YU.PinforYouAPIServer.Entity.UserEntity;
import YU.PinforYouAPIServer.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    // 사용자 찾기 by Id
    public Optional<UserEntity> findById(String id){
        return loginRepository.findById(id);
    }

    // ios 클라이언트로부터 전달받은 사용자 정보를 저장
    @Transactional
    public void save(UserEntity user) {
        loginRepository.save(user);
    }

    /*// ios 클라이언트로부터 전달받은 pw를 인증
    public boolean checkPassword(UserEntity user, String authPw) {
        return user.getPw().equals(authPw);
    }*/
}