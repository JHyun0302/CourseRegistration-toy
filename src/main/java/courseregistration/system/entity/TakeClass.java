package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TakeClass {
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

    @Builder
    public TakeClass(User user, Classes classes) {
        this.user = user;
        this.classes = classes;
    }
}
