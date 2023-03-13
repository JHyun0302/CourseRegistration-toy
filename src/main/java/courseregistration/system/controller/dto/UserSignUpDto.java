package courseregistration.system.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class UserSignUpDto {
    @NotEmpty(message = "이름은 꼭 입력하셔야 합니다.")
    private String username;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirm;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNumber;
    private Long majorId;

    public UserSignUpDto(String username, String loginId, String password, String passwordConfirm, String email, String phoneNumber, Long majorId) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.majorId = majorId;
    }
}
