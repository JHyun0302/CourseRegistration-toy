package courseregistration.system.controller;

import courseregistration.system.config.SessionConst;
import courseregistration.system.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        if (loginUser == null) {
            return "home/home";
        }
        model.addAttribute("user", loginUser);

        return "home/loginHome";
    }
}
