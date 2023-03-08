package courseregistration.system.entity;

import courseregistration.system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class UserTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testEntity() {
        //given
        Major software = new Major("software");
        em.persist(software);

        User tester1 = new User("test1", "test1!", "테스터1", software, "test1@xxx.com", "010-1234-5678");
        User tester2 = new User("test2", "test2!", "테스터2", software, "test2@xxx.com", "010-5678-1234");
        em.persist(tester1);
        em.persist(tester2);

        em.flush();
        em.clear();

        //when
        List<User> users = em.createQuery("select u from User u", User.class).getResultList();
        //then
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

}