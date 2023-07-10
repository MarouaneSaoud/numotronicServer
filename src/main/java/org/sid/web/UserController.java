package org.sid.web;

import lombok.Data;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;


    @GetMapping("/users")
    public List<AppUser> users(){
        return  accountService.allUser();
    }


    @PostMapping("/register")
    public AppUser register(@RequestBody  UserForm userForm){
        return  accountService.saveUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
    }


    @PostMapping("/addRoleToUser")
    public void AddRoleTo(@RequestBody  RoleUserForm roleUserForm){
          accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }

    @PostMapping("/addRole")
    public void AddRoleTo(@RequestBody AppRole role){
        accountService.saveRole(role);
    }



}
@Data
class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
}
@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
