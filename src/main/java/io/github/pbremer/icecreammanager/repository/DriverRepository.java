package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.Driver;

/**
 * Interface to persist {@link DriverRepository} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface DriverRepository extends IsActiveSerchable<Driver, String> {

}
