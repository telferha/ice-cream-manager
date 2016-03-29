package io.github.pbremer.icecreammanager.flatfilecontents;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class RouteFlatFileContainer extends AbstractFlatFileContainer {

    private String actionCode;
    private String routeNumber;
    private List<String> cityLabel;

    /**
     * @return the actionCode
     */
    public String getActionCode() {
	return actionCode;
    }

    /**
     * @param actionCode
     *            the actionCode to set
     */
    public void setActionCode(String actionCode) {
	this.actionCode = actionCode;
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

    /**
     * @return the cityLabel
     */
    public List<String> getCityLabel() {
	return cityLabel;
    }

    /**
     * @param cityLabel
     *            the cityLabel to set
     */
    public void setCityLabel(List<String> cityLabel) {
	this.cityLabel = cityLabel;
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
