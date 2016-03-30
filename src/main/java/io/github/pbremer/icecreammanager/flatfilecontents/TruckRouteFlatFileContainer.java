/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class TruckRouteFlatFileContainer extends AbstractFlatFileContainer {

    private String truckNumeber;
    private String routeNumber;

    /**
     * @return the truckNumeber
     */
    public String getTruckNumeber() {
	return truckNumeber;
    }

    /**
     * @param truckNumeber
     *            the truckNumeber to set
     */
    public void setTruckNumeber(String truckNumeber) {
	this.truckNumeber = truckNumeber;
    }

    /**
     * @return the routeNumber
     */
    public String getRouteNumber() {
	return routeNumber;
    }

    /**
     * @param routeNumber
     *            the routeNumber to set
     */
    public void setRouteNumber(String routeNumber) {
	this.routeNumber = routeNumber;
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
