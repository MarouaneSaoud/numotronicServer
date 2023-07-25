package org.sid.api;

import lombok.RequiredArgsConstructor;
import org.sid.dao.entity.Company;
import org.sid.dao.entity.Device;
import org.sid.dao.entity.DeviceGroup;
import org.sid.dao.repository.CompanyRepository;
import org.sid.dto.company.CompanyToSave;
import org.sid.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
   /* @PostConstruct
    void init(){
        CompanyToSave companyToSave = new CompanyToSave("X","test","test","test",1234,"test","x@gmail.com","test","test",12345,1234,1234,1234,"test","test");
        companyService.addCompany(companyToSave);
    }*/
    @GetMapping("/")
    public List<Company> companies(){
        return companyService.company_list();
    }
    @PostMapping("/save")
    public Company company(@RequestBody CompanyToSave companyToSave){
        return companyService.addCompany(companyToSave);
    }
    @GetMapping("/{id}")
    public Company companyById(@PathVariable String id){
        return companyService.getCompanyById(id);
    }
    
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        companyService.delete(id);
    }
    
    @GetMapping("/device/{id}")
    public List<Device> devices(@PathVariable String id){
        Company companyById = companyService.getCompanyById(id);
        List<Device> devicesByCompany = companyService.findDevicesByCompany(companyById);
        return devicesByCompany;
    }
    
    @GetMapping("/groupDevice/{id}")
    public List<DeviceGroup> deviceGroups(@PathVariable String  id){
        Company companyById = companyService.getCompanyById(id);
        List<DeviceGroup> deviceGroups = companyService.findDeviceGroupsByCompany(companyById);
        return deviceGroups;
    }
    @GetMapping ("/connected/{email}")
    public Company company(@PathVariable String email){
        return companyService.getCompanyForLoggedInUser(email);
    }
}
