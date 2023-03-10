package courseregistration.system.controller.dto.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MajorDto {
    private String majorName;

    public MajorDto(String majorName) {
        this.majorName = majorName;
    }
}
