package io.github.pbremer.icecreammanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	ROUTE, TRUCK, CITY, INVENTORY, TRUCK_ROUTE, DRIVER_TRUCK, ROUTE_PRICE
    }

    public enum Status {
	READY, WAITING, PROCESSED, NOT_READY
    }

    @Id
    @Column(name = "FILE_TYPE")
    private FileType fileType;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_SIZE")
    private long fileSize;

    @Column(name = "FILE_CONTENTS")
    @Lob
    private byte[] contents;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "SEQUENCE_NUMBER")
    private int sequenceNumber;

    @Column(name = "DAY")
    @Temporal(TemporalType.DATE)
    private Date day;

    @Column(name = "AMMOUNT_OF_DATA")
    private int ammountOfData;

    private int parsedAmmountOfData;

    public InputFileMetaData() {
	fileSize = 0L;
	contents = new byte[0];
	ammountOfData = 0;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public long getFileSize() {
	return fileSize;
    }

    public void setFileSize(long fileSize) {
	this.fileSize = fileSize;
    }

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

    public int getAmmountOfData() {
	return ammountOfData;
    }

    public void setAmmountOfData(int ammountOfData) {
	this.ammountOfData = ammountOfData;
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
