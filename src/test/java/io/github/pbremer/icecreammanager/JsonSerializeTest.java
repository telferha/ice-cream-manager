package io.github.pbremer.icecreammanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.pbremer.icecreammanager.entity.City;
import io.github.pbremer.icecreammanager.entity.Zone;

/**
 * Class to test JSON serialization to ensure no circular serialization. To use,
 * uncomment <code>@Test</code> above methods you wish to test.
 * 
 * @author Patrick Bremer
 */
public class JsonSerializeTest {

    private ObjectMapper mapper;

    @Before
    public void setup() {
	mapper = new ObjectMapper();
    }

    // @Test
    public void testJsonSeralize() throws JsonProcessingException {
	City city = new City();
	city.setCityName("Royal Oak");
	city.setCreatedDate(new Date());
	city.setLastModifiedDate(new Date());
	List<Zone> zones = new ArrayList<Zone>();
	Zone zone1 = new Zone();
	zone1.setCity(city);
	zone1.setZoneName("Zone1");
	zones.add(zone1);
	Zone zone2 = new Zone();
	zone2.setCity(city);
	zone2.setZoneName("Zone2");
	zones.add(zone2);
	city.setZones(zones);

	System.out.println(mapper.writeValueAsString(city));
	System.out.println(mapper.writeValueAsString(city.getZones()));
    }
}
