/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
public class InputFileContents<T> {

    private int sequenceNumber;
    private Date day;
    private List<T> contents;
    private int footerNumber;

    public InputFileContents() {
	contents = new ArrayList<T>();
    }

    public void add(T line) {
	contents.add(line);
    }

    public List<T> getContents() {
	return contents;
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
     * @param contents
     *            the contents to set
     */
    public void setContents(List<T> contents) {
	this.contents = contents;
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
