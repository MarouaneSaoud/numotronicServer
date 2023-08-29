package org.numo.service.impl;

import lombok.RequiredArgsConstructor;
import org.numo.dao.entity.*;
import org.numo.dao.repository.ClientRepository;
import org.numo.dao.repository.CompanyRepository;
import org.numo.dao.repository.DeviceGroupRepository;
import org.numo.dao.repository.DeviceRepository;
import org.numo.dto.company.CompanyDeviceAllocatePercentage;
import org.numo.dto.company.CompanyStatistic;
import org.numo.dto.company.CompanyToSave;
import org.numo.dto.company.DeviceGroupWithDeviceCountDTO;
import org.numo.dto.device.DeviceToSend;
import org.numo.dto.device.DevicesFromAPI;
import org.numo.error.BusinessException;
import org.numo.error.TechnicalException;
import org.numo.functions.CalculateDeviceStatus;
import org.numo.functions.GenerateRandomPassword;
import org.numo.functions.GetDevice;
import org.numo.functions.impl.GetDeviceFromApi;
import org.numo.service.AccountService;
import org.numo.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final DeviceRepository deviceRepository;
    private final AccountService accountService;
    private final ClientRepository  clientRepository;
    private final DeviceGroupRepository deviceGroupRepository;

    @Override
    public List<Company> company_list() {
        return companyRepository.findAll();
    }
    @Override
    public Company addCompany(CompanyToSave companyToSave) {
        try {

            Company company=new Company();

            GenerateRandomPassword grp= new GenerateRandomPassword();
            String mdp = grp.generateRandomPassword(8);
            System.out.println(mdp);
            AppUser appUser = accountService.saveUser(companyToSave.getEmail(), companyToSave.getName(), mdp, mdp);

            if (appUser==null) throw new BusinessException("User Not Created");
            else accountService.addRoleToUser(appUser.getUsername(), "MANAGER");

                company.setAccount(appUser);
                company.setId(UUID.randomUUID().toString());
                company.setName(companyToSave.getName());
                company.setAltName(companyToSave.getAltName());
                company.setCin(companyToSave.getCin());
                company.setAddress(companyToSave.getAddress());
                company.setPostalCode(companyToSave.getPostalCode());
                company.setDepartment(companyToSave.getDepartment());
                company.setEmail(companyToSave.getEmail());
                company.setWebsite(companyToSave.getWebsite());
                company.setSkype(companyToSave.getSkype());
                company.setIdrc(companyToSave.getIdrc());
                company.setIdif(companyToSave.getIdif());
                company.setPatent(companyToSave.getPatent());
                company.setCnss(companyToSave.getCnss());
                company.setCountry(companyToSave.getCountry());
                company.setLogo(companyToSave.getLogo());

                company.setDeviceGroups(new ArrayList<>());
                company.setDevices(new ArrayList<>());
                company.setClients(new ArrayList<>());



        return companyRepository.save(company);
        }
        catch (TechnicalException t){
            throw new TechnicalException("ERROR");
        }
    }

    @Override
    public List<DeviceToSend> findDevicesByCompany(Company company) {
        GetDevice getDevice = new GetDeviceFromApi();
        List<DevicesFromAPI> devicesFromAPI = getDevice.AllDevices(); // add time out
        Set<String> activeImies = new HashSet<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<DeviceToSend> listDevices = new ArrayList<>();

        for (DevicesFromAPI devices : devicesFromAPI) {
            List<Device> devicesByCompany = companyRepository.findDevicesByCompany(company);

            for (Device d : devicesByCompany) {
                    Device deviceByImei = deviceRepository.findDeviceByImei(d.getImei());
                    if (deviceByImei != null && devices.getIMEI().equals(d.getImei())) {
                        DeviceToSend device = new DeviceToSend();
                        device.setId(deviceByImei.getId());
                        device.setImei(devices.getIMEI());
                        device.setTime(devices.getLastSeen());
                        device.setFirmware(devices.getFirware());
                        device.setConfiguration(devices.getConfig());
                        if (deviceByImei.getCompany() != null) {
                            device.setCompany(deviceByImei.getCompany().getName());
                        }
                        if (deviceByImei.getClient() != null) {
                            device.setClient(deviceByImei.getClient().getName());
                        }
                        if (deviceByImei.getDeviceGroup() != null) {
                            device.setGroup(deviceByImei.getDeviceGroup().getName());
                        }
                        CalculateDeviceStatus status = new CalculateDeviceStatus();
                        device.setStatusDevice(status.calculateDeviceStatus(devices.getLastSeen(), currentDateTime));
                        activeImies.add(devices.getIMEI());
                        listDevices.add(device);
                    }
                }
            }
            return listDevices;
    }

    @Override
    public List<DeviceGroup> findDeviceGroupsByCompany(Company company) {
        List<DeviceGroup> deviceGroupsByCompany = companyRepository.findDeviceGroupsByCompany(company);
        return deviceGroupsByCompany;

    }
    @Override
    public List<Client> findClientsByCompany(Company company) {
        List<Client> clientsByCompany = companyRepository.findClientsByCompany(company);
        return clientsByCompany;
    }
    @Override
    public void delete(String id) {
        Company company= companyRepository.findById(id).orElse(null);
        if(company!=null) {
            List<Client> clients = companyRepository.findClientsByCompany(company);
            List<Device> devices = companyRepository.findDevicesByCompany(company);
            List<DeviceGroup> deviceGroups = companyRepository.findDeviceGroupsByCompany(company);
            clientRepository.deleteAll(clients);
            deviceGroupRepository.deleteAll(deviceGroups);
            for (Device d : devices){
                d.setCompany(null);
                d.setDeviceGroup(null);
            }
            companyRepository.delete(company);
            accountService.delete(company.getAccount().getId());
        }else {
            throw new BusinessException("Company not found");
        }

    }

    @Override
    public Company getCompanyForLoggedInUser(String email) {
            Company company = companyRepository.findByAccount_Username(email);
            return company;
    }

    @Override
    public Company getCompanyById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Long countDevicesByCompany(Company company) {
        long l = companyRepository.countDevicesByCompany(company);
        return l;
    }

    @Override
    public Long countClientsByCompany(Company company) {
        long l = companyRepository.countClientsByCompany(company);
        return l;
    }

    @Override
    public Long countDeviceGroupsByCompany(Company company) {
        return companyRepository.countDeviceGroupsByCompany(company);
    }

    @Override
    public Long countCompany() {
        return companyRepository.count();
    }

    public List<DeviceGroupWithDeviceCountDTO> getDeviceGroupsWithDeviceCountByCompany(Company company) {
        return companyRepository.findDeviceGroupsWithDeviceCountByCompany(company)
                .stream()
                .map(result -> new DeviceGroupWithDeviceCountDTO((DeviceGroup) result[0], (Long) result[1]))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDeviceAllocatePercentage percentageOfAffectedDevices(Company company) {
        List<Device> devices = company.getDevices();
        double affected =0;
        double NotAffected =0;

        for(Device d : devices){
            if(d.getClient()==null){
                NotAffected++;
            }else {
                affected++;
            }
        }
        CompanyDeviceAllocatePercentage percentage = new CompanyDeviceAllocatePercentage();
        if(affected ==0 && NotAffected==0){
            percentage.setAffected(0.0)  ;
            percentage.setNotAffected(0.0);
        }
        else {
        percentage.setAffected((affected/(affected+NotAffected))*100)  ;
        percentage.setNotAffected((NotAffected/(affected+NotAffected))*100);
        }
        return percentage;
    }

    @Override
    public CompanyStatistic companyStatistic(Company company) {
        CompanyStatistic companyStatistic = new CompanyStatistic();
        List<Client> clients = company.getClients();
        List<DeviceGroup> deviceGroups = company.getDeviceGroups();
        List<Device> devices = company.getDevices();
        companyStatistic.setClient(clients.isEmpty() ? 0 : clients.size() + 1);
        companyStatistic.setGroup(deviceGroups.isEmpty() ? 0 : deviceGroups.size() + 1);
        companyStatistic.setDevice(devices.isEmpty() ? 0 : devices.size() + 1);


        return companyStatistic;
    }

    @Override
    public List<Company> getTop5CompaniesByDeviceCount() {
        List<Company> top5CompaniesByDeviceCount = companyRepository.findTop5CompaniesByDeviceCount();
        return top5CompaniesByDeviceCount.subList(0, Math.min(top5CompaniesByDeviceCount.size(), 5));
    }


}
