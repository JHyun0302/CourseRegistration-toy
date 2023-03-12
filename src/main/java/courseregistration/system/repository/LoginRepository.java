package courseregistration.system.repository;

import courseregistration.system.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepository {
    private final UserRepository userRepository;
    private static Map<Long, User> store = new HashMap<>();

    private static long sequence = 0L;

    public User save(User user) {
        log.info("save: user={}", user);
        store.put(user.getUserId(), user);
        return user;
    }

    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clearStore() {
        store.clear();
    }
}
