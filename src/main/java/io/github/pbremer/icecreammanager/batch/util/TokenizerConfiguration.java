/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Patrick Bremer
 */
@Component
@PropertySource("classpath:ice-cream-manager.properties")
public class TokenizerConfiguration {

    private static Logger log =
            LoggerFactory.getLogger(TokenizerConfiguration.class);

    @Bean
    @Value("${inputfile.truckroute.names}")
    public String[] truckRouteNames(String names) {
	return namesBuilder(names);
    }

    @Bean
    @Value("${inputfile.truckroute.columns}")
    public Range[] truckRouteColumns(String columns) {
	return rangeBuilder(columns);
    }

    private static String[] namesBuilder(String names) {
	log.debug("Converting {} for tokenizer", names);
	List<String> columnNames = Arrays.asList(names.split(","));
	// columnNames.add("Error Column");
	return columnNames.toArray(new String[columnNames.size()]);
    }

    private static Range[] rangeBuilder(String columns) {
	log.debug("Converting {} for tokenizer", columns);
	List<Range> ranges = new ArrayList<Range>();
	for (String range : columns.split(",")) {
	    ranges.add(new Range(Integer.valueOf(range.split("-")[0]),
	            Integer.valueOf(range.split("-")[1])));
	}
	new Range(ranges.get(ranges.size() - 1).getMax() + 1,
	        Integer.MAX_VALUE);
	return ranges.toArray(new Range[ranges.size()]);
    }
}
