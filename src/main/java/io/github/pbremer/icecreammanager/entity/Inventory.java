package io.github.pbremer.icecreammanager.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Inventory extends EntitySupport {

    private static final long serialVersionUID = 1440448106184050947L;

    @Column(name = "INVENTORY_DAY")
    @Temporal(TemporalType.DATE)
    private Date inventoryDay;

    @ManyToOne
    @JoinColumn(name = "TRUCK_INSTANCE_ID")
    private TruckInstance truckInstance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ICE_CREAM_ID")
    private IceCream iceCream;

    @Column(name = "AMMOUNT")
    private int ammount;

}
