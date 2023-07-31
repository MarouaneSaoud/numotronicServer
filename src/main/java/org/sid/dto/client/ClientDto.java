package org.sid.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.dto.appUser.AppUserDto;
import org.sid.dto.company.CompanyDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String id;
    private String name;
    private String cin;
    private String address;
    private int postalCode;
    private String email;
    private AppUserDto accountDto;
    private CompanyDto companyDto;
}
