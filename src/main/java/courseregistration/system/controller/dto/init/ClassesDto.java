package courseregistration.system.controller.dto.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassesDto {
    private CourseDto course;

    private int classNumber;
    private String professorName;
    private int maxStudentNum;
    private int curStudentNum;

    public ClassesDto(CourseDto course, int classNumber, String professorName, int maxStudentNum, int curStudentNum) {
        this.course = course;
        this.classNumber = classNumber;
        this.professorName = professorName;
        this.maxStudentNum = maxStudentNum;
        this.curStudentNum = curStudentNum;
    }
}
