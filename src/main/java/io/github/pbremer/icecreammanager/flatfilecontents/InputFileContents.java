/**
 * 
 */
package io.github.pbremer.icecreammanager.flatfilecontents;

import java.util.Date;
import java.util.List;

/**
 * @author Patrick Bremer
 */
public class InputFileContents<T> {

    private int sequenceNumber;
    private Date day;
    private List<T> contents;
    private int footerNumber;

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

}
