/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.City;
import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.flatfilecontents.CityFlatFileContainer;
import io.github.pbremer.icecreammanager.repository.CityRepository;
import io.github.pbremer.icecreammanager.repository.ZoneRepository;

/**
 * @author Patrick Bremer
 */
public class CityConverter implements Converter<CityFlatFileContainer, City> {

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private ZoneRepository zoneRepo;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public City convert(CityFlatFileContainer source) {
	// if (source.isValid()) {
	City city;
	if (cityRepo.exists(source.getCityName())) {
	    city = cityRepo.getOne(source.getCityName());
	    Hibernate.initialize(city.getZones());
	} else {
	    city = new City();
	}
	city.setActive(true);
	city.setCityName(source.getCityName());
	List<Zone> zoneList = new ArrayList<Zone>();
	Zone zone = new Zone();
	zone.setActive(true);
	zone.setZoneName(source.getCityLabel());
	zone.setCity(city);
	zoneList.add(zone);
	if (city.getZones() == null || city.getZones().isEmpty()) {
	    city.setZones(zoneList);
	} else {
	    zoneList.addAll(city.getZones());
	    city.setZones(zoneList);
	}
	return city;
	// }

	// return null;
    }

    @BeforeWrite
    void beforeWrite(List<? extends City> items) {
	List<Zone> zones = zoneRepo.findAll();
	for (Zone zone : zones) {
	    zone.setActive(false);
	}
	zoneRepo.save(zones);

    }

}
