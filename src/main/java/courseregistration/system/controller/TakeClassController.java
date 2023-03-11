package courseregistration.system.controller;

import courseregistration.system.controller.dto.ClassSearch;
import courseregistration.system.entity.Classes;
import courseregistration.system.entity.Course;
import courseregistration.system.entity.Major;
import courseregistration.system.entity.User;
import courseregistration.system.service.ClassService;
import courseregistration.system.service.CourseService;
import courseregistration.system.service.MajorService;
import courseregistration.system.service.TakeClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TakeClassController {
    private final MajorService majorService;
    private final CourseService courseService;
    private final ClassService classService;
    private final TakeClassService takeClassService;

    @GetMapping("/register")
    public String courseRegistration(@ModelAttribute("classSearch") ClassSearch classSearch,
                                     @RequestParam(value = "msg", required = false) String msg,
                                     Model model) {
        List<Classes> findClasses = classService.findByCourse(classSearch.getCourseId());
        List<Course> findCourses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> findMajors = majorService.findAll();

        model.addAttribute("classes", findClasses);
        model.addAttribute("courses", findCourses);
        model.addAttribute("majors", findMajors);

        model.addAttribute("msg", msg);

        return "courseRegistration";
    }

    @GetMapping("/register/{id}")
    public String courseRegister(@PathVariable("id") Long classId, User user) {
        try {
            takeClassService.save(user.getUserId(), classId);
        } catch (IllegalArgumentException e) {
            return "redirect:/register?msg=" + e.getMessage();
        }
        return "redirect:/register?msg=Success!";
    }

    @GetMapping("/cancel/{id}")
    public String courseCancel(@PathVariable("id") Long takeId) {
        takeClassService.delete(takeId);

        return "redirect:/myCourses?msg=Success!";
    }
}
