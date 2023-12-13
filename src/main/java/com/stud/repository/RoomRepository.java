package com.stud.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, String>{

	Optional<Room> findTopByOrderByIdDesc();

	Optional<Room> findByRoomNo(int roomNo);

	List<Room> findAllByRoomNoNotIn(List<Integer> bookedRooms);

	Optional<List<Room>> findAllByStatus(String string);


}
