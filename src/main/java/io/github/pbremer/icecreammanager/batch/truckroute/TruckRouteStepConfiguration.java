package io.github.pbremer.icecreammanager.batch.truckroute;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;

import io.github.pbremer.icecreammanager.flatfilecontents.TruckRouteFlatFileContainer;

/**
 * @author Patrick Bremer
 */
public class TruckRouteStepConfiguration {

    @Bean
    public ItemReader<TruckRouteFlatFileContainer> truckRouteReader(
            @Qualifier("truckRouteLineMapper") LineMapper<TruckRouteFlatFileContainer> lineMapper) {
	FlatFileItemReader<TruckRouteFlatFileContainer> reader =
	        new FlatFileItemReader<TruckRouteFlatFileContainer>();
	reader.setLineMapper(lineMapper);
	return reader;
    }

    @Bean
    public LineMapper<TruckRouteFlatFileContainer> truckRouteLineMapper(
            @Qualifier("truckRouteFieldSetMapper") FieldSetMapper<TruckRouteFlatFileContainer> fieldSetMapper) {
	DefaultLineMapper<TruckRouteFlatFileContainer> lineMapper =
	        new DefaultLineMapper<TruckRouteFlatFileContainer>();
	lineMapper.setFieldSetMapper(fieldSetMapper);

	return lineMapper;
    }

    @Bean
    public FieldSetMapper<TruckRouteFlatFileContainer>
            truckRouteFieldSetMapper() {
	FieldSetMapper<TruckRouteFlatFileContainer> mapper =
	        new FieldSetMapper<TruckRouteFlatFileContainer>() {

	            @Override
	            public TruckRouteFlatFileContainer mapFieldSet(
	                    FieldSet fieldSet) throws BindException {
		        TruckRouteFlatFileContainer container =
	                        new TruckRouteFlatFileContainer();

		        container.setRouteNumber(
	                        fieldSet.readString("Route Number"));
		        container.setTruckNumeber(
	                        fieldSet.readString("Truck Number"));
		        container.setErrorColumnValue(
	                        fieldSet.readString("Error Column"));

		        return container;
	            }
	        };

	return mapper;
    }

    @Bean
    public LineTokenizer truckRouteLineTokenizer(
            @Qualifier("truckRouteNames") String[] names,
            @Qualifier("truckRouteColumns") Range[] columns) {
	FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();

	tokenizer.setNames(names);
	tokenizer.setColumns(columns);
	tokenizer.setStrict(false);

	return tokenizer;
    }
}
