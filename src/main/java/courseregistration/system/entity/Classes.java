package courseregistration.system.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classes extends BaseTimeEntity {
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

    public Classes(Course course, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
        this.course = course;
        this.classNumber = classNumber;
        this.professorName = professorName;
        this.maxStudentNum = maxStudentNum;
        this.curStudentNum = curStudentNum;
    }

    public static Classes createClasses(Course course, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
        return new Classes(course, classNumber, professorName, maxStudentNum, curStudentNum);
    }

    //==수강신청 신청==//
    public void registration() {
        this.curStudentNum++;
    }

    //==수강신청 취소==//
    public void cancel() {
        this.curStudentNum--;
    }

    public boolean isFull() {
        return curStudentNum >= maxStudentNum;
    }


}
