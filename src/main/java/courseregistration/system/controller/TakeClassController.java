package courseregistration.system.controller;

import courseregistration.system.controller.dto.ClassSearch;
import courseregistration.system.controller.dto.UserResponseDto;
import courseregistration.system.entity.Classes;
import courseregistration.system.entity.Course;
import courseregistration.system.entity.Major;
import courseregistration.system.entity.User;
import courseregistration.system.exception.RegistrationException;
import courseregistration.system.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TakeClassController {
    private final MajorService majorService;
    private final UserService userService;
    private final CourseService courseService;
    private final ClassService classService;
    private final TakeClassService takeClassService;

    @GetMapping("/register/{username}")
    public String course(@ModelAttribute("classSearch") ClassSearch classSearch,
                         @PathVariable("username") String username,
                         Model model) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        List<Classes> classes = classService.findByCourse(classSearch.getCourseId());
        List<Course> courses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> majors = majorService.findAll();


        model.addAttribute("classes", classes);
        model.addAttribute("majors", majors);
        model.addAttribute("courses", courses);
        model.addAttribute("username", username);

        return "order/courseRegistration";
    }

    @PostMapping("/register/{username}")
    public String courseRegister(@PathVariable("username") String username,
                                 @RequestParam("classId") Long classId,
                                 RedirectAttributes redirectAttributes) {

        UserResponseDto userResponseDto = userService.findDtoByUsername(username);
        try {
            takeClassService.save(userResponseDto.getUserId(), classId);
        } catch (RegistrationException e) {
            return "redirect:/register?msg=" + e.getMessage();
        }

        redirectAttributes.addAttribute("username", userResponseDto.getUsername());
        return "redirect:/myCourses/{username}";
    }


    @GetMapping("/myCourses/{username}")
    public String myCourseList(@PathVariable String username, Model model) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        model.addAttribute("username", userResponseDto.getUsername());
        model.addAttribute("takeClasses", userResponseDto.getTakeClasses());

        return "order/myCourseList";
    }

    @PostMapping("/myCourses/{username}/{takeId}/cancel")
    public String courseCancel(@PathVariable("username") String username,
                               @PathVariable("takeId") Long takeId,
                               RedirectAttributes redirectAttributes, Model model) {
        UserResponseDto userResponseDto = userService.findDtoByUsername(username);

        User deleteUser = takeClassService.delete(takeId);
        String deleteUsername = deleteUser.getUsername();

        redirectAttributes.addAttribute("username", deleteUsername);
        return "redirect:/myCourses/{username}";
    }
}
