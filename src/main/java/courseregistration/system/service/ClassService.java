package courseregistration.system.service;

import courseregistration.system.entity.Classes;
import courseregistration.system.repository.ClassesRepository;
import courseregistration.system.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
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
