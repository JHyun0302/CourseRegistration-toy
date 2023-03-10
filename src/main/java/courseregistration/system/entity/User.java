package courseregistration.system.entity;

import courseregistration.system.controller.dto.UserUpdateRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String email;
    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<TakeClass> takeClasses;

    //    @Builder(builderClassName = "UserSignupBuilder", builderMethodName = "signupBuilder")
    public User(String loginId, String password, String username, Major major, Role role, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.major = major;
        this.role = Role.STUDENT;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.takeClasses = new ArrayList<>();
    }

    public static User createStudent(String loginId, String password, String username, Major major, String email, String phoneNumber) {
        return new User(loginId, password, username, major, Role.STUDENT, email, phoneNumber);
    }

    public static User createAdmin(String loginId, String password, String username, Major major, String email, String phoneNumber) {
        return new User(loginId, password, username, major, Role.ADMIN, email, phoneNumber);
    }

    public void update(UserUpdateRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.phoneNumber = requestDto.getPhoneNumber();
    }

    //==수강 신청==//
    public void registration(TakeClass saveClass) {
        this.takeClasses.add(saveClass);
    }

    //==수강 취소==//
    public void cancel(TakeClass deleteClass) {
        this.takeClasses.remove(deleteClass);
    }

}
