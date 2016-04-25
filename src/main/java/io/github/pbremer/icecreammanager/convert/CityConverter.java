/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.City;
import io.github.pbremer.icecreammanager.entity.Zone;
import io.github.pbremer.icecreammanager.flatfilecontents.CityFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class CityConverter implements Converter<CityFlatFileContainer, City> {

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public City convert(CityFlatFileContainer source) {
	// if (source.isValid()) {
	City city = new City();
	city.setActive(true);
	city.setCityName(source.getCityName());
	List<Zone> zoneList = new ArrayList<Zone>();
	Zone zone = new Zone();
	zone.setActive(true);
	zone.setZoneName(source.getCityLabel());
	zone.setCity(city);
	zoneList.add(zone);
	city.setZones(zoneList);
	return city;
	// }

	// return null;
    }

}
