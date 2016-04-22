/**
 * 
 */
package io.github.pbremer.icecreammanager.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Patrick Bremer
 */
@MappedSuperclass
public abstract class ActivatableEntitySupport extends EntitySupport {

    private static final long serialVersionUID = -7476967985808192355L;

    public ActivatableEntitySupport() {
    }

    @Column(name = "ACTIVE")
    private boolean active = true;

    /**
     * @return the isActive
     */
    public boolean isActive() {
	return active;
    }

    /**
     * @return the isActive
     */
    public boolean getActive() {
	return active;
    }

    /**
     * @param isActive
     *            the isActive to set
     */
    public void setActive(boolean active) {
	this.active = active;
    }

}
