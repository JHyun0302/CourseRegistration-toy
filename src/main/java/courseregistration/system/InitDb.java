package courseregistration.system;

import courseregistration.system.controller.dto.init.*;
import courseregistration.system.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Component
@Transactional
@RequiredArgsConstructor
public class InitDb {
    private final EntityManager em;

    public void dbInit1() {
        MajorDto major = createMajor("software");
        em.persist(major);

        UserDto user = createUser("idA", "pwA!", "userA", major, Role.STUDENT, "userA@xxx.com", "010-1234-5678");
        em.persist(user);

        CourseDto cLanguage = createCourse(major, "C Programming");
        em.persist(cLanguage);

        ClassesDto classes1 = createClasses(cLanguage, 1, "lee", 50, 0);
        em.persist(classes1);

        TakeClassesDto takeClass = createTakeClass(user, classes1);
        em.persist(takeClass);
    }

    public void dbInit2() {
        MajorDto major = createMajor("computerScience");
        em.persist(major);

        UserDto user = createUser("idB", "pwB!", "userB", major, Role.STUDENT, "userB@xxx.com", "010-2222-5678");
        em.persist(user);

        CourseDto javaLanguage = createCourse(major, "Java Programming");
        em.persist(javaLanguage);

        ClassesDto classes2 = createClasses(javaLanguage, 2, "kim", 30, 0);
        em.persist(classes2);

        TakeClassesDto takeClass = createTakeClass(user, classes2);
        em.persist(takeClass);
    }

    private static TakeClassesDto createTakeClass(UserDto user, ClassesDto classes) {
        TakeClassesDto takeClasses = new TakeClassesDto(user, classes);
        takeClasses.setUser(user);
        takeClasses.setClasses(classes);

        return takeClasses;
    }


    private static ClassesDto createClasses(CourseDto cLanguage, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
        ClassesDto classes = new ClassesDto();
        classes.setCourse(cLanguage);
        classes.setClassNumber(classNumber);
        classes.setProfessorName(professorName);
        classes.setMaxStudentNum(maxStudentNum);
        classes.setCurStudentNum(curStudentNum);
        return classes;
    }

    private static CourseDto createCourse(MajorDto major, String subjectName) {
        CourseDto course = new CourseDto();
        course.setMajor(major);
        course.setCourseName(subjectName);
        return course;
    }

    private static UserDto createUser(String loginId, String password, String username, MajorDto major, Role role, String email, String phoneNumber) {
        UserDto user = new UserDto();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setUsername(username);
        user.setMajor(major);
        user.setRole(role);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

    private static MajorDto createMajor(String major) {
        MajorDto majorDto = new MajorDto();
        majorDto.setMajorName(major);
        return majorDto;
    }

}
