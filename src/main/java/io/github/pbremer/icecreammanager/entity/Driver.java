package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DRIVER")
public class Driver extends EntitySupport {

    private static final long serialVersionUID = -4384329683864675619L;

    @Id
    @Column(name = "DRIVER_ID", updatable = false)
    private String driverId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "driver")
    private List<DriverInstance> driverInstances;

    @Column(name = "WAGE")
    private BigDecimal wage;

}
