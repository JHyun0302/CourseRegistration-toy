package courseregistration.system.controller;

import courseregistration.system.controller.dto.UserResponseDto;
import courseregistration.system.controller.dto.UserSignUpDto;
import courseregistration.system.controller.dto.UserUpdateRequestDto;
import courseregistration.system.entity.Major;
import courseregistration.system.service.MajorService;
import courseregistration.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final MajorService majorService;

    @GetMapping("/signup")
    public String signup(@ModelAttribute("signUpDto") UserSignUpDto signUpDto, Model model) {
        List<Major> majors = majorService.findAll();

        model.addAttribute("majors", majors);
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@Valid @ModelAttribute UserSignUpDto userSignUpDto, Model model, BindingResult bindingResult) {
        userService.join(userSignUpDto);

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        List<Major> majors = majorService.findAll();
        model.addAttribute("majors", majors);
        return "redirect:/";
    }


    @GetMapping("/myCourses/{username}")
    public String myCourseList(@PathVariable String username, Model model) {
        UserResponseDto userResponseDto = userService.findByUsername(username);

        model.addAttribute("username", userResponseDto.getUsername());
        model.addAttribute("takeClasses", userResponseDto.getTakeClasses());

        return "myCourseList";
    }

    @GetMapping("/edit/{username}")
    public String update(@PathVariable String username, Model model) {
        UserResponseDto userResponseDto = userService.findByUsername(username);

        model.addAttribute("userUpdateRequestDto", new UserUpdateRequestDto(userResponseDto));
        return "edit";
    }


    @PostMapping("/edit/{username}")
    public String userInfoUpdate(@PathVariable String username, @ModelAttribute UserUpdateRequestDto userUpdateRequestDto, Model model, BindingResult bindingResult) {
        UserResponseDto userResponseDto = userService.findByUsername(username);

        userService.update(userResponseDto.getUserId(), userUpdateRequestDto);

        model.addAttribute("userUpdateRequestDto", userUpdateRequestDto);
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        return "redirect:/";
    }
}
