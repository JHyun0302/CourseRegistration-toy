package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 장바구니
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TakeClass extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "take_id", nullable = false)
    private Long takeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classes;

    public TakeClass(User user, Classes classes) {
        this.user = user;
        this.classes = classes;
    }

    public static TakeClass createTakeClass(User user, Classes classes) {
        return new TakeClass(user, classes);
    }
}
