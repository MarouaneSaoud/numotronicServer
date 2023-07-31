package org.sid.api;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;
import org.sid.dto.user.FindUser;
import org.sid.dto.user.RoleUserForm;
import org.sid.dto.user.UserForm;
import org.sid.error.BusinessException;
import org.sid.service.AccountService;

import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Api(tags = "user", description = "Endpoints to manage users")


public class AuthController extends AbstractController {
    private final AccountService accountService;

    /*@PostConstruct
    void init() {
        accountService.saveUser("admin@numotronic.com", "admin", "1234", "1234");
        accountService.saveUser("aya@numotronic.com", "aya", "1234", "1234");
        accountService.saveUser("manager@numotronic.com", "manager", "1234", "1234");
        accountService.saveRole(new AppRole(null, "SUPER_ADMIN"));
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "MANAGER"));
        accountService.addRoleToUser("admin@numotronic.com", "SUPER_ADMIN");
        accountService.addRoleToUser("aya@numotronic.com", "ADMIN");
        accountService.addRoleToUser("manager@numotronic.com", "MANAGER");
    }
*/


    @GetMapping("/")
    public List<AppUser> users() {
        return accountService.allUser();
    }


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.saveUser(
                userForm.getUsername(),userForm.getName(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
    @PostMapping(value = "/loadUsername")
    public  AppUser loadUserByUserName(@RequestBody FindUser user){
        AppUser appUser = accountService.loadUserByUsername(user.getUserName());
        return Optional.ofNullable(appUser).orElseThrow(() -> new BusinessException("User not found"));
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




