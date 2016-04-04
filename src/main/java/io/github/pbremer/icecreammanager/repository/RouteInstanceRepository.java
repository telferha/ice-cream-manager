package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.RouteInstance;

/**
 * Interface to persist {@link RouteInstance} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface RouteInstanceRepository
        extends DateRangeSearchable<RouteInstance, Long> {

}
