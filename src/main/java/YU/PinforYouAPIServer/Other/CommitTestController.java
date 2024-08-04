package YU.PinforYouAPIServer.Other;

import YU.PinforYouAPIServer.Entity.User;
import YU.PinforYouAPIServer.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommitTestController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    UserRepository userRepository;

    @GetMapping("/get1")
    @ResponseBody
    public ResponseEntity<String> get1() throws JsonProcessingException {
        return null;
    }

    @GetMapping("/get2")
    @ResponseBody
    public ResponseEntity<String> get2() throws JsonProcessingException {
        User user = userRepository.findOne(1L);

        Long age = user.getAge();
        Long id = user.getId();
        String nickname = user.getNickname();
        String tel = user.getTel();
        user.setAge(age);

        return null;
    }

    @GetMapping("/get3")
    @ResponseBody
    public ResponseEntity<String> get3() throws JsonProcessingException {
        return null;
    }
}
