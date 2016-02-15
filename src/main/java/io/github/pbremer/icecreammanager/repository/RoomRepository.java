package io.github.pbremer.icecreammanager.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.pbremer.icecreammanager.entity.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {

}
