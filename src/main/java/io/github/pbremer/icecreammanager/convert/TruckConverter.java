/**
 * 
 */
package io.github.pbremer.icecreammanager.convert;

import org.springframework.core.convert.converter.Converter;

import io.github.pbremer.icecreammanager.entity.Truck;
import io.github.pbremer.icecreammanager.flatfilecontents.TruckFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckConverter
        implements Converter<TruckFlatFileContainer, Truck> {

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.
     * Object)
     */
    @Override
    public Truck convert(TruckFlatFileContainer source) {
	Truck truck = new Truck();
	truck.setTruckNumber(source.getTruckNumber());
	return truck;
    }

}
