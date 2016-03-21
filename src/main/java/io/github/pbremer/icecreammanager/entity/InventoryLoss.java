package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "INVENTORY_LOSS")
public class InventoryLoss extends Inventory {

    private static final long serialVersionUID = -5579994285032744293L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "INVENTORY_LOSS_ID")
    private long id;
}
