package courseregistration.system.service;

import courseregistration.system.entity.Classes;
import courseregistration.system.exception.ResourceNotFoundException;
import courseregistration.system.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassService {
    private final ClassesRepository classesRepository;

    public Classes findById(Long classId) {
        return classesRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Fail: No Classes Info"));
    }

    public List<Classes> findByCourse(Long courseId) {
        return classesRepository.findByCourse(courseId);
    }
}
