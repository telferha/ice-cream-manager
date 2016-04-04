package io.github.pbremer.icecreammanager.repository;

import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.InventoryLoss;

/**
 * Interface to persist {@link InventoryLoss} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface InventoryLossRepository
        extends DateRangeSearchable<InventoryLoss, Long> {

}
