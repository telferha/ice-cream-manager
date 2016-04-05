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
	isActive = true;
    }

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

}
