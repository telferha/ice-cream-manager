package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.Route;

/**
 * Interface to persist {@link Route} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface RouteRepository extends IsActiveSerchable<Route, String> {

}
