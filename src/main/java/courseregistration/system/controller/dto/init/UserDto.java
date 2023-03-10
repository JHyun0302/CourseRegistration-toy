package courseregistration.system.controller.dto.init;

import courseregistration.system.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String loginId;
    private String password;
    private String username;
    private MajorDto major;
    private Role role;
    private String email;
    private String phoneNumber;

    public UserDto(String loginId, String password, String username, MajorDto major, Role role, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.major = major;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}