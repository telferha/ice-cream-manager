/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.BeginDayInventory;
import io.github.pbremer.icecreammanager.repository.BeginDayInventoryRepository;
import io.github.pbremer.icecreammanager.service.BeginDayInventoryService;

/**
 * @author Matthew Stockert
 */
@Service
public class BeginDayInventoryServiceImpl extends AbstractInstanceService<BeginDayInventory, Long>
		implements BeginDayInventoryService {

	private BeginDayInventoryRepository repository;

	@Autowired
	public BeginDayInventoryServiceImpl(BeginDayInventoryRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
