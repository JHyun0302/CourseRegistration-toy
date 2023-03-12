package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Major extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "major_id", nullable = false)
    private Long majorId;
    @Column(nullable = false)
    private String majorName;

    @OneToMany(mappedBy = "major")
    private List<Course> courses = new ArrayList<>();

    public Major(String majorName) {
        this.majorName = majorName;
        this.courses = new ArrayList<>();
    }
}
