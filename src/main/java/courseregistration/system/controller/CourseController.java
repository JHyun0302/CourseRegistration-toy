package courseregistration.system.controller;

import courseregistration.system.controller.dto.ClassSearch;
import courseregistration.system.entity.Course;
import courseregistration.system.entity.Major;
import courseregistration.system.repository.MajorRepository;
import courseregistration.system.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final MajorRepository majorRepository;

    @GetMapping("/courses")
    public String courseList(@ModelAttribute("classSearch") ClassSearch classSearch, Model model) {
        List<Course> courses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> majors = majorRepository.findAll();

        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);

        return "courseList";
    }

    @GetMapping("/courses/{id}")
    public String classList(@PathVariable("id") Long courseId, Model model) {
        Course course = courseService.findById(courseId);

        model.addAttribute("classes", course.getClasses());

        return "classList";
    }
}
