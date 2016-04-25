package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.DefaultInventory;
import io.github.pbremer.icecreammanager.repository.DefaultInventoryRepository;
import io.github.pbremer.icecreammanager.service.DefaultInventoryService;

/**
 * @author Matthew Stockert
 */
@Service
public class DefaultInventoryServiceImpl
        extends AbstractActivatableService<DefaultInventory, Long>
        implements DefaultInventoryService {

    @Autowired
    private DefaultInventoryRepository repository;

    @Autowired
    public DefaultInventoryServiceImpl(DefaultInventoryRepository repository) {
	super(repository);
	this.repository = repository;
    }

    /*
     * (non-Javadoc)
     * @see io.github.pbremer.icecreammanager.service.DefaultInventoryService#
     * deleteAllInBatch()
     */
    @Override
    public void deleteAllInBatch() {
	repository.deleteAllInBatch();
    }
}
