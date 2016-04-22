package io.github.pbremer.icecreammanager.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CITY")
@JsonInclude(Include.NON_EMPTY)
public class City extends ActivatableEntitySupport {

    private static final long serialVersionUID = -9099709758530437246L;

    @Id
    @Column(name = "CITY_NAME")
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "city", fetch = FetchType.EAGER)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "zoneName")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Zone> zones;

    public String getCityName() {
	return cityName;
    }

    public void setCityName(String cityName) {
	this.cityName = cityName;
    }

    public List<Zone> getZones() {
	return zones;
    }

    public void setZones(List<Zone> zones) {
	this.zones = zones;
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.entity.EntitySupport#toString()
     */
    @Override
    public String toString() {
	return ToStringBuilder
	        .reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
	        .toString();
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.entity.EntitySupport#hashCode()
     */
    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this, false);
    }

    /*
     * (non-Javadoc)
     * @see
     * io.github.pbremer.icecreammanager.entity.EntitySupport#equals(java.lang.
     * Object)
     */
    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj, false);
    }

}
