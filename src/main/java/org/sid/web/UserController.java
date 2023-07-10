package org.sid.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {
    private AccountService accountService;


    @GetMapping("/users")
    public List<AppUser> users(){
        return  accountService.allUser();
    }


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public AppUser register(@RequestBody  UserForm userForm){
        return  accountService.saveUser(
                userForm.getUsername(),userForm.getPassword(),userForm.getConfirmedPassword());
    }


    @PostMapping("/addRoleToUser")
    @PreAuthorize("hasRole('ADMIN')")
    public void AddRoleTo(@RequestBody  RoleUserForm roleUserForm){
          accountService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }
    @PreAuthorize("hasRole('ADMIN')")
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
