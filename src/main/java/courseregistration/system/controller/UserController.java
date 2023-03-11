package courseregistration.system.controller;

import courseregistration.system.controller.dto.UserSignUpDto;
import courseregistration.system.entity.Major;
import courseregistration.system.service.MajorService;
import courseregistration.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final MajorService majorService;

    @GetMapping("/signup")
    public String signup(@ModelAttribute("signUpDto") UserSignUpDto signUpDto) {
        return "signup";
    }

    @PostMapping("/signup")
    public String create(UserSignUpDto signUpDto, Model model) {
        try {
            userService.join(signUpDto);
        } catch (Exception e) {
            List<Major> majors = majorService.findAll();

            model.addAttribute("majors", majors);
            model.addAttribute("signUpDto", signUpDto);
            model.addAttribute("msg", e.getMessage());

            return "signup";
        }
        return "redirect:/";
    }

}
