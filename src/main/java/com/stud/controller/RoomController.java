package com.stud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stud.model.Room;
import com.stud.repository.RoomRepository;

@RequestMapping("/room")
@Controller
public class RoomController {

	@Autowired
	RoomRepository repo;

	@RequestMapping("/list")
	public String home(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "room";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		return "room_create";
	}

	@RequestMapping("/save")
	public String save(Room obj) {
		Optional<Room> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getRoomId().substring(5));
			idnum++;
			id = "ROOM0" + idnum;
		} else {
			id = "ROOM064901";
		}
		obj.setRoomId(id);
		obj.setStatus("Available");
		repo.save(obj);
		return "redirect:/room/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Room> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/room/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "room_edit";
	}

	@RequestMapping("/update")
	public String update(Room obj) {
		repo.save(obj);
		return "redirect:/room/list";
	}
}
