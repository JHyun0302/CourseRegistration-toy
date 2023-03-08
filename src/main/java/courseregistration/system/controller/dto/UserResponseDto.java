package courseregistration.system.controller.dto;

import courseregistration.system.entity.Major;
import courseregistration.system.entity.TakeClass;
import courseregistration.system.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserResponseDto {
    private Long userId;
    private String loginId;
    private String username;
    private Major major;
    private String email;
    private String phoneNumber;
    private List<TakeClass> takeClasses;

    public UserResponseDto(User entity) {
        this.userId = entity.getUserId();
        this.loginId = entity.getLoginId();
        this.username = entity.getUsername();
        this.major = entity.getMajor();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.takeClasses = entity.getTakeClasses();
    }
}


