/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.repository.ZoneRepository;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
public class ZoneServiceImpl extends AbstractActivatableService<Zone, String>
        implements ZoneService {

    private ZoneRepository repository;

    /**
     * @param repository
     */
    public ZoneServiceImpl(ZoneRepository repository) {
	super(repository);
	this.repository = repository;
    }

}
