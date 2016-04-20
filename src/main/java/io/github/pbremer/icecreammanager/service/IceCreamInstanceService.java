/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.IceCreamInstance;

/**
 * @author Matthew Stockert
 */
@Service
public interface IceCreamInstanceService extends InstanceService<IceCreamInstance, Long> {

}
