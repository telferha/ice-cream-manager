/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.repository.ZoneRepository;
import io.github.pbremer.icecreammanager.service.ZoneService;

/**
 * @author Patrick Bremer
 */
@Service
public class ZoneServiceImpl extends AbstractActivatableService<Zone, String>
        implements ZoneService {

    private ZoneRepository repository;

    /**
     * @param repository
     */
    @Autowired
    public ZoneServiceImpl(ZoneRepository repository) {
	super(repository);
	this.repository = repository;
    }

}
