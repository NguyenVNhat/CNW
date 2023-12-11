package cnw.utils.filter.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCredential {
    private String username;
    private String password;
    private Role role;
}
