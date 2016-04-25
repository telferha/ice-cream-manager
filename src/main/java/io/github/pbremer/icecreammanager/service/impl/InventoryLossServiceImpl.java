/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.InventoryLoss;
import io.github.pbremer.icecreammanager.repository.IceCreamRepository;
import io.github.pbremer.icecreammanager.repository.InventoryLossRepository;
import io.github.pbremer.icecreammanager.service.InventoryLossService;

/**
 * @author Matthew Stockert
 */
@Service
public class InventoryLossServiceImpl extends AbstractInstanceService<InventoryLoss, Long>
        implements InventoryLossService {

    private InventoryLossRepository repository;

    @Autowired
    public InventoryLossServiceImpl(InventoryLossRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
