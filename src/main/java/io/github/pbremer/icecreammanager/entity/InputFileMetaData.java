package io.github.pbremer.icecreammanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "INPUT_FILE_META_DATA")
public class InputFileMetaData extends EntitySupport {

    private static final long serialVersionUID = 9145706372486551880L;

    public enum FileType {
	ROUTE("routeUpload.txt", "^[ACD].*"),
	TRUCK("truckUpload.txt", "^[0-9].*"),
	CITY("cityUpload.txt", "^(?!T\\s)(?!HD\\s).*"),
	WAREHOUSE("dailyInventory.txt", "^[0-9].*"),
	DRIVER("driverUpload.txt", "^[0-9].*"),
	TRUCK_ROUTE("truckRouteUpload.txt", "^[0-9].*"),
	DRIVER_TRUCK("driverTruck.txt", "^[0-9].*"),
	LOAD_TRUCK("loadTruck.txt", "^[0-9].*"),
	TRUCK_SALES("dailySales.txt", "^[0-9].*"),
	COSTS("cost.txt", "^[0-9].*"),
	ROUTE_PRICE("routePrice.txt", "^[0-9].*");

	private String fileName;
	private String regexCount;

	private FileType(final String fileName, final String regexCount) {
	    this.fileName = fileName;
	    this.regexCount = regexCount;
	}

	public String getFileName() {
	    return fileName;
	}

	public String getRegexCount() {
	    return regexCount;
	}
    }

    public enum Status {
	READY, WAITING, PROCESSED, NOT_READY, ERROR
    }

    @Id
    @Column(name = "FILE_TYPE")
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    // @Column(name = "FILE_CONTENTS")
    // @Lob
    private transient byte[] contents = new byte[0];

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "SEQUENCE_NUMBER")
    private int sequenceNumber;

    @Column(name = "DAY")
    @Temporal(TemporalType.DATE)
    private Date day;

    private transient int parsedAmmountOfData;

    public byte[] getContents() {
	return contents;
    }

    public void setContents(byte[] contents) {
	this.contents = contents;
    }

    public FileType getFileType() {
	return fileType;
    }

    public void setFileType(FileType fileType) {
	this.fileType = fileType;
    }

    public Status getStatus() {
	return status;
    }

    public void setStatus(Status status) {
	this.status = status;
    }

    public int getSequenceNumber() {
	return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
	this.sequenceNumber = sequenceNumber;
    }

    public Date getDay() {
	return day;
    }

    public void setDay(Date day) {
	this.day = day;
    }

    public int getParsedAmmountOfData() {
	return parsedAmmountOfData;
    }

    public void setParsedAmmountOfData(int parsedAmmountOfData) {
	this.parsedAmmountOfData = parsedAmmountOfData;
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
