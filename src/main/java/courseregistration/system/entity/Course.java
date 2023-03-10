package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 전공 과목
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Classes> classes;

    public Course(Major major, String courseName) {
        this.major = major;
        this.courseName = courseName;
    }

    public static Course createCourse(Major major, String courseName) {
        return new Course(major, courseName);
    }
}
