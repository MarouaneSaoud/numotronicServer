package org.sid.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;
import org.sid.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AccountService accountService;


  @PostConstruct
    void init() {
        // Todo à supprimer
        accountService.saveUser("admin@numotronic.com", "1234", "1234");
        accountService.saveUser("aya@numotronic.com", "1234", "1234");
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "USER"));
        accountService.addRoleToUser("admin@numotronic.com", "ADMIN");
        accountService.addRoleToUser("aya@numotronic.com", "USER");
    }

    @GetMapping("/")
    public List<AppUser> users() {
        return accountService.allUser();
    }


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }


    @PostMapping("/addRoleToUser")
    public void AddRoleTo(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @PostMapping("/addRole")
    public void AddRoleTo(@RequestBody AppRole role) {
        accountService.saveRole(role);
    }


}

@Data
class UserForm {
    private String username;
    private String password;
    private String confirmedPassword;
}

@Data
class RoleUserForm {
    private String username;
    private String roleName;
}
