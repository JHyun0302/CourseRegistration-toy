package courseregistration.system.controller.dto;

import courseregistration.system.entity.Course;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClassSearch {
    private Long majorId;
    private Long courseId;

    public ClassSearch(Course course) {
        this.majorId = majorId;
        this.courseId = courseId;
    }
}
