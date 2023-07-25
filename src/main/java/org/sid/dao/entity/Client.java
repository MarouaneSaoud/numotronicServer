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
public class Client {

    @Id
    private String id;
    private  String name;
    private String cin ;
    private String address;
    private int postalcode;
    private String email;
    @OneToOne
    private AppUser account ;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
