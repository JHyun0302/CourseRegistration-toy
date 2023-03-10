package courseregistration.system.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
public class LoginForm {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
