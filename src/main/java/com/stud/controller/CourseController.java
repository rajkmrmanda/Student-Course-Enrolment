package com.stud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stud.model.Course;
import com.stud.model.Room;
import com.stud.repository.CourseRepository;
import com.stud.repository.RoomRepository;

@RequestMapping("/course")
@Controller
public class CourseController {

	@Autowired
	CourseRepository repo;

	@Autowired
	RoomRepository roomRepo;

	@RequestMapping("/list")
	public String home(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "course";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("rooms", roomRepo.findAllByStatus("Available").get());
		return "course_create";
	}

	@RequestMapping("/save")
	public String save(Course obj) {
		Optional<Course> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getCourseId().substring(5));
			idnum++;
			id = "CORS0" + idnum;
		} else {
			id = "CORS064901";
		}
		obj.setCourseId(id);
		repo.save(obj);
		
		Room room = roomRepo.findByRoomNo(obj.getRoomNo()).get();
		room.setStatus("UnAvailable");
		roomRepo.save(room);
		
		return "redirect:/course/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Course obj = repo.findById(id).get();

		Room room = roomRepo.findByRoomNo(obj.getRoomNo()).get();
		room.setStatus("Available");
		roomRepo.save(room);
		
		repo.delete(obj);

		return "redirect:/course/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		model.addAttribute("rooms", roomRepo.findAllByStatus("Available").get());

		return "course_edit";
	}

	@RequestMapping("/update")
	public String update(Course obj) {
		if(repo.findById(obj.getId()).get().getRoomNo() != obj.getRoomNo())
		{
			Room room = roomRepo.findByRoomNo(repo.findById(obj.getId()).get().getRoomNo()).get();
			room.setStatus("Available");
			roomRepo.save(room);
			
			room = roomRepo.findByRoomNo(obj.getRoomNo()).get();
			room.setStatus("UnAvailable");
			roomRepo.save(room);
		}
		repo.save(obj);
		return "redirect:/course/list";
	}
}
