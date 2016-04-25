package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.Zone;

/**
 * Interface to persist {@link Zone} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface ZoneRepository extends IsActiveSerchable<Zone, String> {

}
