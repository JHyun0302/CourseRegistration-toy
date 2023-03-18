package courseregistration.system.service;

import courseregistration.system.entity.Classes;
import courseregistration.system.entity.TakeClass;
import courseregistration.system.entity.User;
import courseregistration.system.exception.RegistrationException;
import courseregistration.system.repository.ClassesRepository;
import courseregistration.system.repository.TakeClassRepository;
import courseregistration.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static courseregistration.system.entity.TakeClass.createTakeClass;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TakeClassService {
    private final TakeClassRepository takeClassRepository;
    private final UserRepository userRepository;
    private final ClassesRepository classesRepository;

    @Transactional
    public Long save(Long userId, Long classId) {
        User user = userRepository.findById(userId).get();
        Classes classes = classesRepository.findById(classId).get();

        if (classes.isFull()) {
            throw new RegistrationException("Fail: Full");
        }

        Optional<TakeClass> getCourse = user.getTakeClasses().stream()
                .filter(takeClass -> takeClass.getClasses().getCourse().equals(classes.getCourse().getCourseId()))
                .findAny();


        if (getCourse.isPresent()) {
            throw new RegistrationException("Fail: Already Registered!");
        }

        TakeClass saveClass = takeClassRepository.save(createTakeClass(user, classes));

        user.registration(saveClass);
        classes.registration();

        return saveClass.getTakeId();
    }

    @Transactional
    public User delete(Long takeId) {
        TakeClass deleteClass = takeClassRepository.findById(takeId).get();

        User user = userRepository.findById(deleteClass.getUser().getUserId()).get();
        user.cancel(deleteClass);

        Classes classes = classesRepository.findById(deleteClass.getClasses().getClassId()).get();
        classes.cancel();

        return user;
    }
}
