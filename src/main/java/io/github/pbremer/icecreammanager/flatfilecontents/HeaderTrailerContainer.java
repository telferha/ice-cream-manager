/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class HeaderTrailerContainer {

    private int sequenceNumber;
    private Date day;
    private int footerNumber;
    private AtomicInteger actualCount;

    public HeaderTrailerContainer() {
	actualCount = new AtomicInteger();
    }

    /**
     * @return the sequenceNumber
     */
    public int getSequenceNumber() {
	return sequenceNumber;
    }

    /**
     * @param sequenceNumber
     *            the sequenceNumber to set
     */
    public void setSequenceNumber(int sequenceNumber) {
	this.sequenceNumber = sequenceNumber;
    }

    /**
     * @return the day
     */
    public Date getDay() {
	return day;
    }

    /**
     * @param day
     *            the day to set
     */
    public void setDay(Date day) {
	this.day = day;
    }

    /**
     * @return the footerNumber
     */
    public int getFooterNumber() {
	return footerNumber;
    }

    /**
     * @param footerNumber
     *            the footerNumber to set
     */
    public void setFooterNumber(int footerNumber) {
	this.footerNumber = footerNumber;
    }

    /**
     * @return the actualCount
     */
    public int getActualCount() {
	return actualCount.get();
    }

    public int incrimentCount() {
	return actualCount.incrementAndGet();
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
