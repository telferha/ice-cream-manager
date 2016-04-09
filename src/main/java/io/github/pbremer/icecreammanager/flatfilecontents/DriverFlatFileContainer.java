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
public class DriverFlatFileContainer extends AbstractFlatFileContainer {

    private String driverNumber;
    private String hourlyWage;

    /**
     * @return the driverNumber
     */
    public String getDriverNumber() {
	return driverNumber;
    }

    /**
     * @param driverNumber
     *            the driverNumber to set
     */
    public void setDriverNumber(String driverNumber) {
	this.driverNumber = driverNumber;
    }

    /**
     * @return the hourlyWage
     */
    public String getHourlyWage() {
	return hourlyWage;
    }

    /**
     * @param hourlyWage
     *            the hourlyWage to set
     */
    public void setHourlyWage(String hourlyWage) {
	this.hourlyWage = hourlyWage;
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
