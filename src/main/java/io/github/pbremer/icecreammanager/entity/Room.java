package io.github.pbremer.icecreammanager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROOMS")
public class Room implements Serializable {
    
    private static final long serialVersionUID = -7636551949751043003L;
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    // @OneToMany(orphanRemoval = true, mappedBy = "room")
    // private List<Chair> chair;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Room [id=").append(id).append(", name=").append(name);
        // .append(", chair=").append(chair).append("]");
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        // result = prime * result + ((chair == null) ? 0 : chair.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (!(obj instanceof Room)) {
            return false;
        }
        Room other = (Room) obj;
        /*
         * if (chair == null) {
         * if (other.chair != null) {
         * return false;
         * }
         * } else if (!chair.equals(other.chair)) {
         * return false;
         * }
         */ if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}
