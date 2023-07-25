package org.sid.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serialNum;
    @Column(unique = true)
    @NotNull
    private int imei;
    private String description;
    private Date createdAt;
    @ManyToOne
    private Reference reference;
    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(optional = true)
    @JoinColumn(name = "device_group_id")
    private DeviceGroup deviceGroup;

}
