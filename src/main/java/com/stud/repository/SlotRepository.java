package com.stud.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stud.model.Slot;

@Repository
public interface SlotRepository extends CrudRepository<Slot, String>{

	Optional<Slot> findTopByOrderByIdDesc();

	Optional<List<Slot>> findAllByProfId(String profId);
	Optional<Slot> findBySlotId(String slotId);

	Optional<List<Slot>> findAllByProfIdAndDay(String profId, String day);


}
