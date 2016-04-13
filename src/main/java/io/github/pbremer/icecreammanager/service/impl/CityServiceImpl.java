/**
 * 
 */
package io.github.pbremer.icecreammanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.City;
import io.github.pbremer.icecreammanager.repository.CityRepository;
import io.github.pbremer.icecreammanager.service.CityService;

/**
 * @author Patrick Bremer
 */
@Service
public class CityServiceImpl extends AbstractActivatableService<City, String>
        implements CityService {

    private CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
	super(repository);
	this.repository = repository;
    }
}
