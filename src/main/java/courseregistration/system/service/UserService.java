package courseregistration.system.service;

import courseregistration.system.controller.dto.UserResponseDto;
import courseregistration.system.controller.dto.UserSignUpDto;
import courseregistration.system.controller.dto.UserUpdateRequestDto;
import courseregistration.system.entity.User;
import courseregistration.system.repository.MajorRepository;
import courseregistration.system.repository.UserRepository;
import courseregistration.system.service.exception.LoginIdException;
import courseregistration.system.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    
    @Transactional
    public Long join(UserSignUpDto signUpDto) {
        userRepository.findByLoginId(signUpDto.getLoginId())
                .ifPresent(user -> {
                    throw new LoginIdException("Failed: Already Exist ID!");
                });
        if (!signUpDto.getPassword().equals(signUpDto.getPasswordConfirm())) {
            throw new LoginIdException("Failed: Please Check Password!");
        }

        User student = User.createStudent(signUpDto.getLoginId(), signUpDto.getPassword(), signUpDto.getUsername(),
                majorRepository.findById(signUpDto.getMajorId()).get(), signUpDto.getEmail(), signUpDto.getPhoneNumber());
        return userRepository.save(student).getUserId();
    }

    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Fail: No UserResponseDto Info"));
        return new UserResponseDto(user);
    }

    @Transactional
    public Long update(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Fail: No UserRequestDto Info"));

        user.update(requestDto);

        return userId;
    }

}
