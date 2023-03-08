package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classes {
    @Id
    @GeneratedValue
    @Column(name = "class_id", nullable = false)
    private Long classId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    private int classNumber;
    @Column(nullable = false)
    private String professorName;
    @Column(nullable = false)
    private int maxStudentNum;
    @Column(nullable = false)
    private int curStudentNum;

    @Builder
    public Classes(Course course, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
        this.course = course;
        this.classNumber = classNumber;
        this.professorName = professorName;
        this.maxStudentNum = maxStudentNum;
        this.curStudentNum = curStudentNum;
    }
}
