package org.numo.dao.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    private String id;

    private  String name;
    private String altName;
    private String cin_rc ;

    @CreationTimestamp
    private Date createdAt;

    private String address;
    private int postalCode;
    private String department ;
    private String email;
    private String website;
    private String tel ;
    private String skype ;
    private int idif;
    private int patent;
    private int cnss ;
    private String country;

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
