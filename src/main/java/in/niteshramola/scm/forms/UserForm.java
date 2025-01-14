package in.niteshramola.scm.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String about;
}
