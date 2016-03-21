package io.github.pbremer.icecreammanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TRUCK_INSTANCE")
public class TruckInstance extends EntitySupport {

    private static final long serialVersionUID = 7969346198180317639L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRUCK_INSTANCE_ID")
    private long truckInstanceId;

    @Temporal(TemporalType.DATE)
    @Column(name = "TRUCK_DAY")
    private Date truckDay;

    @ManyToOne
    @JoinColumn(name = "TRUCK_NUMBER")
    private Truck truck;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<TruckInventory> inventory;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "truckInstance")
    private List<TruckInventory> inventoryLoss;

    @OneToOne
    @JoinColumn(name = "ROUTE_DAY")
    private RouteInstance routeInstance;

    @OneToOne
    @JoinColumn(name = "DRIVER_DAY")
    private DriverInstance driverInstance;

}
