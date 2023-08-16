package org.numo.dao.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

    @Id
    private String id;

    private  String name;
    private String cin ;
    private String address;
    private int postalCode;

    @CreationTimestamp
    private Date createdAt;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private AppUser account ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Device> devices;
}
