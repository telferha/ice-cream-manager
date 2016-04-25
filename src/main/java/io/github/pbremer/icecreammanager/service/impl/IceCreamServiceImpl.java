/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.IceCream;
import io.github.pbremer.icecreammanager.repository.IceCreamRepository;
import io.github.pbremer.icecreammanager.service.IceCreamService;

/**
 * @author Matthew Stockert
 */
@Service
public class IceCreamServiceImpl extends AbstractActivatableService<IceCream, String>
        implements IceCreamService {

    private IceCreamRepository repository;

    @Autowired
    public IceCreamServiceImpl(IceCreamRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
