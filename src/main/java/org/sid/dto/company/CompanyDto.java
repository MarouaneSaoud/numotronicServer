package org.sid.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.dto.appUser.AppUserDto;
import org.sid.dto.client.ClientDto;
import org.sid.dto.device.DeviceDto;
import org.sid.dto.groupeDevice.DeviceGroupDto;

import java.util.Set;

// CompanyDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String id;
    private String name;
    private String altname;
    private String cin;
    private String address;
    private int postalcode;
    private String departement;
    private String email;
    private String website;
    private String skype;
    private int idrc;
    private int idif;
    private int patent;
    private int cnss;
    private String country;
    private String logo;
    private AppUserDto account;
    private Set<ClientDto> clients;
    private Set<DeviceGroupDto> deviceGroups;
    private Set<DeviceDto> devices;
}
