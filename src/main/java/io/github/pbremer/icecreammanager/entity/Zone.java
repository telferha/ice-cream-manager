package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ZONE")
public class Zone extends EntitySupport {

    private static final long serialVersionUID = 1497610113204826980L;

    @Id
    @Column(name = "ZONE_NAME", updatable = false)
    private String zoneName;

    @ManyToOne
    @JoinColumn(name = "CITY_NAME")
    private City city;

    @ManyToOne
    @JoinColumn(name = "ROUTE_DAY")
    private RouteInstance routeInstance;

}
