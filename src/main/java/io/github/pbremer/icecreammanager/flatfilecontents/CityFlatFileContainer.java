package io.github.pbremer.icecreammanager.flatfilecontents;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Class that directly maps to the city upload file
 * 
 * @author Patrick Bremer
 */
public class CityFlatFileContainer extends AbstractFlatFileContainer {

    private String cityLabel;
    private String cityName;
    private String state;

    /**
     * @return the cityLabel
     */
    public String getCityLabel() {
	return cityLabel;
    }

    /**
     * @param cityLabel
     *            the cityLabel to set
     */
    public void setCityLabel(String cityLabel) {
	this.cityLabel = cityLabel;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
	return cityName;
    }

    /**
     * @param cityName
     *            the cityName to set
     */
    public void setCityName(String cityName) {
	this.cityName = cityName;
    }

    /**
     * @return the state
     */
    public String getState() {
	return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
	this.state = state;
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
