package org.numo.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.Client;
import org.numo.dao.entity.Company;
import org.numo.dao.entity.Device;
import org.numo.dao.entity.DeviceGroup;
import org.numo.dto.company.CompanyToSave;
import org.numo.dto.device.DeviceToSend;
import org.numo.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Api(tags = "company", description = "Endpoints to manage companies")
public class CompanyController {

    private final CompanyService companyService;

  /* @PostConstruct
    void init(){
        CompanyToSave companyToSave = new CompanyToSave("aya120","test","test","test",13467,"test","crf@gmail.com","test","test",12345,1234,1234,1234,"test","test");
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
    
    @GetMapping("/device/{email}")
    public List<DeviceToSend> devices(@PathVariable String email){
        Company companyForLoggedInUser = companyService.getCompanyForLoggedInUser(email);
        Company company = companyService.getCompanyById(companyForLoggedInUser.getId());
        List<DeviceToSend> devicesByCompany = companyService.findDevicesByCompany(company);
        return devicesByCompany;
    }
    
    @GetMapping("/deviceGroup/{email}")
    public List<DeviceGroup> deviceGroups(@PathVariable String  email){
        Company companyForLoggedInUser = companyService.getCompanyForLoggedInUser(email);
        Company company = companyService.getCompanyById(companyForLoggedInUser.getId());
        List<DeviceGroup> deviceGroups = companyService.findDeviceGroupsByCompany(company);
        return deviceGroups;
    }
    @GetMapping("/client/{email}")
    public List<Client> clients(@PathVariable String email ){
        Company companyForLoggedInUser = companyService.getCompanyForLoggedInUser(email);
        Company company = companyService.getCompanyById(companyForLoggedInUser.getId());
        List<Client> clientsByCompany = companyService.findClientsByCompany(company);
        return clientsByCompany ;
    }
    @GetMapping ("/connected/{email}")
    public Company company(@PathVariable String email){
        return companyService.getCompanyForLoggedInUser(email);
    }
    @GetMapping("/device/count/{id}")
    public Long countDevices(@PathVariable String id){
        Company company = companyService.getCompanyById(id);
        long devicesByCompany = companyService.countDevicesByCompany(company);
        return devicesByCompany;
    }
    @GetMapping("/group/count/{id}")
    public Long countGroup(@PathVariable String id){
        Company company = companyService.getCompanyById(id);
        Long countDeviceGroups = companyService.countDeviceGroupsByCompany(company);
        return countDeviceGroups;
    }
    @GetMapping("/client/count/{id}")
    public Long countClient(@PathVariable String id){
        Company company = companyService.getCompanyById(id);
        Long countClients = companyService.countClientsByCompany(company);
        return countClients;
    }
    @GetMapping("/count")
    public Long countClient(){
        Long countClients = companyService.countCompany();
        return countClients;
    }
}
