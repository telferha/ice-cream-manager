/**
 * 
 */
package io.github.pbremer.icecreammanager.batch.processor;

import org.springframework.batch.item.validator.ValidatingItemProcessor;

import io.github.pbremer.icecreammanager.validator.HeaderValidator;

/**
 * @author Patrick Bremer
 */
public class VerifyHeader extends ValidatingItemProcessor<HeaderValidator> {

}
