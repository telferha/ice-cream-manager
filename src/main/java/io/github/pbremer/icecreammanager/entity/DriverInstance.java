package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DRIVER_INSTANCE")
public class DriverInstance extends EntitySupport {

    private static final long serialVersionUID = -5196627091470377041L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DRIVER_INSTANCE_ID")
    private long driverInstanceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "DRIVER_DAY")
    private Date driverDay;

    @OneToOne
    @JoinColumn(name = "TRUCK_DAY")
    private TruckInstance truckInstance;

    @ManyToOne
    @JoinColumn(name = "DRIVER_ID")
    private Driver driver;

    @Column(name = "HOURS")
    private BigDecimal hours;
}
