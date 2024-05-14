package kr.or.nextit.springmvc.regist;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private final String email;
    private String name;
    private String password;
    private String confirmPassword;
}
