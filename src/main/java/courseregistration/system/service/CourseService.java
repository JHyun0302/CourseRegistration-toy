package courseregistration.system.service;

import courseregistration.system.entity.Course;
import courseregistration.system.exception.ResourceNotFoundException;
import courseregistration.system.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CourseService {

    private final CourseRepository courseRepository;

    public Course findById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Fail: No Course Info"));
    }

    public List<Course> findByMajor(Long majorId) {
        return courseRepository.findByMajor(majorId);
    }
}
