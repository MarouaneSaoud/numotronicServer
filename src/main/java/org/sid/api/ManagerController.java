package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Manager;
import org.sid.service.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {

    private ManagerService managerService;
    @GetMapping("/")
    public List<Manager> managers(){
        return managerService.managerlist();
    }
    @PostMapping("/add")
    public Manager save(Manager manager){
        return managerService.AddManager(manager);
    }
    @GetMapping("/delete")
    public void delete(Long id){
        managerService.delete(id);
    }
}
