/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.EndDayInventory;
import io.github.pbremer.icecreammanager.repository.EndDayInventoryRepository;
import io.github.pbremer.icecreammanager.service.EndDayInventoryService;

/**
 * @author Matthew Stockert
 */
@Service
public class EndDayInventoryServiceImpl extends AbstractInstanceService<EndDayInventory, Long>
        implements EndDayInventoryService {

    private EndDayInventoryRepository repository;

    @Autowired
    public EndDayInventoryServiceImpl(EndDayInventoryRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
