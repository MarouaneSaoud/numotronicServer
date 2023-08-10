package org.numo.api;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.numo.dao.entity.AppRole;
import org.numo.dao.entity.AppUser;
import org.numo.dto.user.RoleUserForm;
import org.numo.dto.user.UserForm;
import org.numo.error.BusinessException;
import org.numo.service.AccountService;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Api(tags = "user", description = "Endpoints to manage users")


public class AuthController extends AbstractController {
    private final AccountService accountService;

   /* @PostConstruct
    void init() {
        accountService.saveUser("admin@numotronic.com", "admin", "1234", "1234");
        accountService.saveUser("aya@numotronic.com", "aya", "1234", "1234");
        accountService.saveUser("manager@numotronic.com", "manager", "1234", "1234");
        accountService.saveRole(new AppRole(null, "SUPER_ADMIN"));
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "MANAGER"));
        accountService.saveRole(new AppRole(null, "CLIENT"));
        accountService.addRoleToUser("admin@numotronic.com", "SUPER_ADMIN");
        accountService.addRoleToUser("aya@numotronic.com", "ADMIN");
        accountService.addRoleToUser("manager@numotronic.com", "MANAGER");
    }*/


    @GetMapping("/")
    public List<AppUser> users() {
        return accountService.allUser();
    }


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm) {
        return accountService.saveUser(
                userForm.getUsername(),userForm.getName(), userForm.getPassword(), userForm.getConfirmedPassword());
    }
    @GetMapping(value = "/loadUsername/{username}")
    public  AppUser loadUserByUserName(@PathVariable String username){
        AppUser appUser = accountService.loadUserByUsername(username);
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
    @PostMapping("/register/admin")
    public AppUser registerAdmin(@RequestBody UserForm userForm) {
        return accountService.saveUserAdmin(
                userForm.getUsername(),userForm.getName(), userForm.getPassword(), userForm.getConfirmedPassword());
    }


    @GetMapping("/findAdminUsers")
    public List<AppUser> findAdminUsers() {
        return accountService.findAdminUsers();
    }

    @GetMapping("/disableUser/{id}")
    public Boolean disableUser(@PathVariable Long id){
        return accountService.DisableUser(id);
    }

    @GetMapping("/unableUser/{id}")
    public Boolean unableUser(@PathVariable Long id){
        return accountService.UnableUser(id);
    }
}




