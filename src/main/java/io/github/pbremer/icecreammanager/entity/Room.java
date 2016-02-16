package io.github.pbremer.icecreammanager.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "ROOMS")
public class Room implements Serializable {
    
    private static final long serialVersionUID = -7636551949751043003L;
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @OneToMany(orphanRemoval = true, mappedBy = "room", fetch = FetchType.EAGER)
    private List<Chair> chair;
    
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
    
    public List<Chair> getChair() {
        return chair;
    }
    
    public void setChair(List<Chair> chair) {
        this.chair = chair;
    }
    
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("id", id).append("name", name);
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chair == null) ? 0 : chair.hashCode());
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
        if (chair == null) {
            if (other.chair != null) {
                return false;
            }
        } else if (!chair.equals(other.chair)) {
            return false;
        }
        if (id != other.id) {
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
