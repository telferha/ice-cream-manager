/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.IceCreamInstance;
import io.github.pbremer.icecreammanager.repository.IceCreamInstanceRepository;
import io.github.pbremer.icecreammanager.service.IceCreamInstanceService;

/**
 * @author Matthew Stockert
 */
@Service
public class IceCreamInstanceServiceImpl extends AbstractInstanceService<IceCreamInstance, Long>
        implements IceCreamInstanceService {

    private IceCreamInstanceRepository repository;

    @Autowired
    public IceCreamInstanceServiceImpl(IceCreamInstanceRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
