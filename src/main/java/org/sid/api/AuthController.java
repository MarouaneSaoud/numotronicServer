package org.sid.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.sid.dao.entity.AppRole;
import org.sid.dao.entity.AppUser;
import org.sid.dto.user.RoleUserForm;
import org.sid.dto.user.UserForm;
import org.sid.error.BusinessException;
import org.sid.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AccountService accountService;

/*  @PostConstruct
    void init() {
        // Todo à supprimer
        accountService.saveUser("admin@numotronic.com", "1234", "1234");
        accountService.saveUser("aya@numotronic.com", "1234", "1234");
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "USER"));
        accountService.addRoleToUser("admin@numotronic.com", "ADMIN");
        accountService.addRoleToUser("aya@numotronic.com", "USER");
    }
    */


    @GetMapping("/")
    public List<AppUser> users() {
        return accountService.allUser();
    }


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.saveUser(
                userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
    @PostMapping(value = "/loadUsername" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public  AppUser loadUserByUserName(@RequestBody User user){
        AppUser appUser = accountService.loadUserByUsername(user.getUsername());
         return Optional.ofNullable(appUser)
                .orElseThrow(() -> new BusinessException("User not found"));
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




