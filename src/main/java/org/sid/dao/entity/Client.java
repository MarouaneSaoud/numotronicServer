package org.sid.dao.entity;
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
    private int postalcode;
    @CreationTimestamp
    private Date createdAt;
    private String email;
    @OneToOne
    private AppUser account ;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
