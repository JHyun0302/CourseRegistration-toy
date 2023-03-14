package courseregistration.system.controller;

import courseregistration.system.controller.dto.UserResponseDto;
import courseregistration.system.controller.dto.UserSignUpDto;
import courseregistration.system.controller.dto.UserUpdateRequestDto;
import courseregistration.system.entity.Major;
import courseregistration.system.service.MajorService;
import courseregistration.system.service.UserService;
import courseregistration.system.service.exception.LoginIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        model.addAttribute("signUpDto", signUpDto);
        model.addAttribute("majors", majors);
        return "signup";
    }

    @PostMapping("/signup")
    public String saveUser(@Validated @ModelAttribute("signUpDto") UserSignUpDto signUpDto, BindingResult bindingResult, Model model) {
        List<Major> majors = majorService.findAll();
        model.addAttribute("majors", majors);

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "/signup";
        }

        try {
            Long savedUser = userService.join(signUpDto);
        } catch (LoginIdException e) {
            bindingResult.reject("PW & PW_confirm Not the same.", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "/signup";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{username}")
    public String userInfo(@PathVariable String username, Model model) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        model.addAttribute("userUpdateRequestDto", new UserUpdateRequestDto(userResponseDto));
        return "edit/userInfo";
    }


    @GetMapping("/edit/{username}/edit")
    public String update(@PathVariable String username, Model model) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        model.addAttribute("userUpdateRequestDto", new UserUpdateRequestDto(userResponseDto));
        return "edit/editForm";
    }


    @PostMapping("/edit/{username}/edit")
    public String userInfoUpdate(@PathVariable String username, @Validated @ModelAttribute("userUpdateRequestDto") UserUpdateRequestDto userUpdateRequestDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        if (bindingResult.hasErrors()) {
            log.info("error= {}", bindingResult);
            return "edit/editForm";
        }
        model.addAttribute("userUpdateRequestDto", userUpdateRequestDto);
        userService.update(userResponseDto.getUserId(), userUpdateRequestDto);

        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/edit/{username}";
    }
}
