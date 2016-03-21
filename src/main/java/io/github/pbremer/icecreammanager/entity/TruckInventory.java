package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "TRUCK_INVENTORY")
public class TruckInventory extends Inventory {

    private static final long serialVersionUID = 1155957628079417625L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRUCK_INVENTORY_ID")
    private long id;

}
