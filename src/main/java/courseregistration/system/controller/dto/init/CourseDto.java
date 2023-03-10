package courseregistration.system.controller.dto.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {

    private MajorDto major;

    private String courseName;

    public CourseDto(MajorDto major, String courseName) {
        this.major = major;
        this.courseName = courseName;
    }
}
