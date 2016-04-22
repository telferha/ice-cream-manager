/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.IceCream;

/**
 * @author Matthew Stockert
 */
@Service
public interface IceCreamService extends ActivatableService<IceCream, String> {

}
