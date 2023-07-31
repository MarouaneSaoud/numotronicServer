package org.numo.dao.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    private String id;

    private  String name;
    private String altname;
    private String cin ;

    @CreationTimestamp
    private Date createdAt;

    private String address;
    private int postalCode;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private AppUser account;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "company")
    private List<Client> clients;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "company" )
    private List<DeviceGroup> deviceGroups;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "company")
    private List<Device> devices;

}
