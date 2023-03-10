package courseregistration.system.controller.dto.init;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TakeClassesDto {
    private UserDto user;

    private ClassesDto classes;

    public TakeClassesDto(UserDto user, ClassesDto classes) {
        this.user = user;
        this.classes = classes;
    }
}
