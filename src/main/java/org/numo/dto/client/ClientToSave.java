package org.numo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientToSave {
    private String name;
    private String cin;
    private String address;
    private int postalCode;
    private String email;
    private String CompanyEmail;
}
