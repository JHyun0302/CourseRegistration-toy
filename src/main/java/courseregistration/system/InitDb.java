package courseregistration.system;

import courseregistration.system.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Major major = createMajor("art");
            User user = createUser("123", "123", "user123", major, Role.STUDENT, "user123@xxx.com", "010-1111-1111");
            Course course = createCourse(major, "artist");
            Classes classes = createClasses(course, 1, "son", 30, 0);
            TakeClass takeClass = createTakeClass(user, classes);
            persist(major, user, course, classes, takeClass);
        }

        public void dbInit2() {
            Major major = createMajor("software");
            User user = createUser("test1", "test1!", "userA", major, Role.STUDENT, "userA@xxx.com", "010-1234-5678");
            Course course = createCourse(major, "C Programming");
            Classes classes = createClasses(course, 1, "lee", 50, 0);
            TakeClass takeClass = createTakeClass(user, classes);
            persist(major, user, course, classes, takeClass);
        }

        public void dbInit3() {
            Major major = createMajor("computerScience");
            User user = createUser("test2", "test2!", "userB", major, Role.STUDENT, "userB@xxx.com", "010-2222-5678");
            Course course = createCourse(major, "Java Programming");
            Classes classes = createClasses(course, 2, "kim", 30, 0);
            TakeClass takeClass = createTakeClass(user, classes);
            persist(major, user, course, classes, takeClass);
        }

        private void persist(Major major, User user, Course course, Classes classes, TakeClass takeClass) {
            em.persist(major);
            em.persist(user);
            em.persist(course);
            em.persist(classes);
            em.persist(takeClass);
        }

        public static TakeClass createTakeClass(User user, Classes classes) {
            return new TakeClass(user, classes);
        }

        public static Classes createClasses(Course course, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
            return new Classes(course, classNumber, professorName, maxStudentNum, curStudentNum);
        }

        public static Course createCourse(Major major, String courseName) {
            return new Course(major, courseName);
        }

        public static User createUser(String loginId, String password, String username, Major major, Role role, String email, String phoneNumber) {
            return new User(loginId, password, username, major, role, email, phoneNumber);
        }

        public static Major createMajor(String majorName) {
            return new Major(majorName);
        }
    }
}

