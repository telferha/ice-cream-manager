package io.github.pbremer.icecreammanager.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "ICE_CREAM")
public class IceCream extends EntitySupport {

    private static final long serialVersionUID = 2908680501990930010L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ICE_CREAM_ID", updatable = false)
    private long iceCreamId;

    @Column(name = "ICE_CREAM_NAME")
    private String iceCreamName;

    @Column(name = "SALES_PRICE")
    private BigDecimal salesPrice;

    @Column(name = "SUPPLIER_PRICE")
    private BigDecimal supplierPrice;

    @Column(name = "DAY", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date day;

}
