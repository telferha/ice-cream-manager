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
@Table(name = "ROUTE_INSTANCE")
public class RouteInstance extends EntitySupport {

    private static final long serialVersionUID = 6686558272033820280L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROUTE_INSTANCE_ID")
    private long routeInstanceId;

    @Column(name = "ROUTE_DAY")
    @Temporal(TemporalType.DATE)
    private Date routeDay;

    @ManyToOne
    @JoinColumn(name = "ROUTE_ID")
    private Route route;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeInstance")
    private List<Zone> zones;

    @OneToOne
    @JoinColumn(name = "TRUCK_DAY")
    private TruckInstance truckInstance;
}
