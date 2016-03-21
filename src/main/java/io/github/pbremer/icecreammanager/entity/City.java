package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "CITY")
public class City extends EntitySupport {

    private static final long serialVersionUID = -9099709758530437246L;

    @Id
    @Column(name = "CITY_NAME", updatable = false)
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "city")
    private List<Zone> zones;

}
