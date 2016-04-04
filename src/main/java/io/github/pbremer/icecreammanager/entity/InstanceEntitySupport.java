/**
 * 
 */
package io.github.pbremer.icecreammanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Patrick Bremer
 */
@MappedSuperclass
public class InstanceEntitySupport extends EntitySupport {

    private static final long serialVersionUID = 9068366936195264820L;

    @Column(name = "DAY")
    @Temporal(TemporalType.DATE)
    private Date day;

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
