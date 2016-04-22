/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;

import io.github.pbremer.icecreammanager.flatfilecontents.HeaderTrailerContainer;

/**
 * @author Patrick Bremer
 */
public class VerifyTrailerCount
        extends ValidatingItemProcessor<HeaderTrailerContainer> {

}
