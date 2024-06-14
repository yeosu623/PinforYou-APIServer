package YU.PinforYouAPIServer.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalRegisterDTO {

    // 이메일(id), 비밀번호, 비밀번호 확인, 이름, 휴대폰 번호, 성별, 나이, 카테고리
    private String id;
    private String pw;
    private String name;
    private String tel;
    private String sex;
    private Integer age;
    private String interest;

}