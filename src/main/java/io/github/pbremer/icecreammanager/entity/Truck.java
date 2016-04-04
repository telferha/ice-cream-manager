package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "TRUCK")
@JsonInclude(Include.NON_EMPTY)
public class Truck extends EntitySupport {

    private static final long serialVersionUID = -4092699031897866819L;

    @Id
    @Column(name = "TRUCK_NUMBER", updatable = false)
    private String truckNumber;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "truck")
    private List<TruckInstance> truckInstances;

    public String getTruckNumber() {
	return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
	this.truckNumber = truckNumber;
    }

    public List<TruckInstance> getTruckInstances() {
	return truckInstances;
    }

    public void setTruckInstances(List<TruckInstance> truckInstances) {
	this.truckInstances = truckInstances;
    }

    @Override
    public String toString() {
	return ToStringBuilder
	        .reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
	        .toString();
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

}
