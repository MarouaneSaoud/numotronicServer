package org.sid.dao.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Company {

    @Id
    private String id;
    private  String name;
    private String altname;
    private String cin ;
    private String address;
    private int postalcode;
    private String departement ;
    private String email;
    private String website;
    private String skype ;
    private int idrc ;
    private int idif;
    private int patent;
    private int cnss ;
    private String country;
    private String logo;
    @OneToOne
    private AppUser account;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Client> clients;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<DeviceGroup> deviceGroups;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Device> devices;

}
