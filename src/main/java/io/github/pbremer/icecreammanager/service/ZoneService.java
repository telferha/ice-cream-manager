/**
 * 
 */
package io.github.pbremer.icecreammanager.service;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.Zone;

/**
 * @author Patrick Bremer
 */
@Service
public interface ZoneService extends ActivatableService<Zone, String> {

}
