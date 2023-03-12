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
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Major major = createMajor("software");
            em.persist(major);

            User user = createUser("test1", "test1!", "userA", major, Role.STUDENT, "userA@xxx.com", "010-1234-5678");
            em.persist(user);

            Course cLanguage = createCourse(major, "C Programming");
            em.persist(cLanguage);

            Classes classes1 = createClasses(cLanguage, 1, "lee", 50, 0);
            em.persist(classes1);

            TakeClass takeClass = createTakeClass(user, classes1);
            em.persist(takeClass);
        }

        public void dbInit2() {
            Major major = createMajor("computerScience");
            em.persist(major);

            User user = createUser("test2", "test2!", "userB", major, Role.STUDENT, "userB@xxx.com", "010-2222-5678");
            em.persist(user);

            Course javaLanguage = createCourse(major, "Java Programming");
            em.persist(javaLanguage);

            Classes classes2 = createClasses(javaLanguage, 2, "kim", 30, 0);
            em.persist(classes2);

            TakeClass takeClass = createTakeClass(user, classes2);
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

