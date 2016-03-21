package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TRUCK")
public class Truck extends EntitySupport {

    private static final long serialVersionUID = -4092699031897866819L;

    @Id
    @Column(name = "TRUCK_NUMBER", updatable = false)
    private String truckNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "truck")
    private List<TruckInstance> truckInstances;

}
