package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "ROUTE")
public class Route extends EntitySupport {

    private static final long serialVersionUID = 4315982774076916956L;

    @Id
    @Column(name = "ROUTE_ID", updatable = false)
    private String routeId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "route")
    private List<RouteInstance> routeInstances;

}
