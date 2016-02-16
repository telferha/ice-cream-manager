package io.github.pbremer.icecreammanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pbremer.icecreammanager.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
