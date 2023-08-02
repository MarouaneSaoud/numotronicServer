package org.numo.dao.entity;

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

public class DeviceGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    private String name;

    @OneToMany(mappedBy = "deviceGroup", cascade = CascadeType.ALL)
    private List<Device> devices;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
