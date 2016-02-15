package io.github.pbremer.icecreammanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHAIRS")
public class Chair implements Serializable {
    
    private static final long serialVersionUID = -5528801717281043935L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "TYPE", length = 20, nullable = false)
    private String type;
    
    @Column(name = "NUMBER_OF_LEGS", nullable = false)
    private int numberOfLegs;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Room room;
    
    public long getId() {
        return id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public int getNumberOfLegs() {
        return numberOfLegs;
    }
    
    public void setNumberOfLegs(final int numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(final Room room) {
        this.room = room;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Chair [id=").append(id).append(", type=").append(type)
                .append(", numberOfLegs=").append(numberOfLegs)
                .append(", room=").append(room).append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + numberOfLegs;
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Chair)) {
            return false;
        }
        Chair other = (Chair) obj;
        if (id != other.id) {
            return false;
        }
        if (numberOfLegs != other.numberOfLegs) {
            return false;
        }
        if (room == null) {
            if (other.room != null) {
                return false;
            }
        } else if (!room.equals(other.room)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }
    
}
