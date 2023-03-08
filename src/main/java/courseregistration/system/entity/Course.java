package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Builder;
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
public class Course {
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

    @Builder
    public Course(Major major, String courseName, List<Classes> classes) {
        this.major = major;
        this.courseName = courseName;
        this.classes = classes;
    }
}
