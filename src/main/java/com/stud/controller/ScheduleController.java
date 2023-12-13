package com.stud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stud.model.Course;
import com.stud.model.Enroll;
import com.stud.model.Professor;
import com.stud.model.Room;
import com.stud.model.Schedule;
import com.stud.model.Slot;
import com.stud.repository.CourseRepository;
import com.stud.repository.EnrollRepository;
import com.stud.repository.ProfessorRepository;
import com.stud.repository.RoomRepository;
import com.stud.repository.ScheduleRepository;
import com.stud.repository.SlotRepository;

@RequestMapping("/sche")
@Controller
public class ScheduleController {

	@Autowired
	ScheduleRepository repo;

	@Autowired
	SlotRepository slotRepo;

	@Autowired
	ProfessorRepository profRepo;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	EnrollRepository enRepo;

	@RequestMapping("/list")
	public String home(Model model, HttpServletRequest req) {
		if (req.getSession().getAttribute("usertype").equals("admin")) {
			model.addAttribute("datalist", repo.findAll());
			model.addAttribute("slotlist", slotRepo.findAll());
		} else if (req.getSession().getAttribute("usertype").equals("student")) {
			List<Enroll> enrollList = enRepo.findAllByStudId(req.getSession().getAttribute("userid").toString()).get();
			List<String> enrollStr = new ArrayList<>();
			for (Enroll e : enrollList) {
				enrollStr.add(e.getScheId());
			}
			model.addAttribute("datalist", repo.findAllByScheIdNotIn(enrollStr).get());
			model.addAttribute("slotlist", slotRepo.findAll());
		} else if (req.getSession().getAttribute("usertype").equals("professor")) {
			model.addAttribute("datalist",
					repo.findAllByProfId(req.getSession().getAttribute("userid").toString()).get());
			model.addAttribute("slotlist", slotRepo.findAll());
		}
		return "sche";
	}

	@RequestMapping("/enrolls")
	public String Enrollments(Model model, HttpServletRequest req) {
		if (req.getSession().getAttribute("usertype").equals("admin")) {
			model.addAttribute("datalist", enRepo.findAll());
		} else if (req.getSession().getAttribute("usertype").equals("student")) {
			List<Enroll> enrollList = enRepo.findAllByStudId(req.getSession().getAttribute("userid").toString()).get();
			model.addAttribute("datalist", enrollList);
		} else if (req.getSession().getAttribute("usertype").equals("professor")) {
			List<Enroll> enrollList = enRepo.findAllByFirstName(req.getSession().getAttribute("name").toString()).get();
			model.addAttribute("datalist", enrollList);
		}
		return "enrolls";
	}

	@RequestMapping("/save")
	public String save(Schedule obj) {
		Optional<Schedule> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getScheId().substring(5));
			idnum++;
			id = "SCHE0" + idnum;
		} else {
			id = "SCHE064901";
		}
		obj.setScheId(id);

		Slot slot = slotRepo.findBySlotId(obj.getSlotId()).get();
		obj.setProfId(slot.getProfId());
		obj.setDay(slot.getDay());
		obj.setTime(slot.getTime());
		obj.setFirstName(slot.getFirstName());

		Professor prof = profRepo.findByProfId(slot.getProfId()).get();
		obj.setCourse(prof.getCourse());

		System.out.println("course  : " + obj.getCourse());
		Course course = courseRepo.findByCourse(obj.getCourse()).get();
		obj.setCourseId(course.getCourseId());
		obj.setRoomNo(course.getRoomNo());

		Room room = roomRepo.findByRoomNo(obj.getRoomNo()).get();
		obj.setAvlSeats(room.getCapacity());

		repo.save(obj);
		return "redirect:/sche/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Schedule> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/sche/list";
	}

	@RequestMapping("/enroll")
	public String enroll(RedirectAttributes ra, @RequestParam String id, Model model, HttpServletRequest req) {
		Schedule sche = repo.findById(id).get();

		Optional<List<Enroll>> enList = enRepo
				.findAllByStudIdAndDate(req.getSession().getAttribute("userid").toString(), sche.getScheDate());

		if (enList.get().isEmpty()) {
			if (sche.getAvlSeats() > 0) {
				sche.setAvlSeats(sche.getAvlSeats() - 1);
				repo.save(sche);

				Enroll enroll = new Enroll();
				Optional<Enroll> idobj = enRepo.findTopByOrderByIdDesc();
				String eid = null;
				if (idobj.isPresent()) {
					int idnum = Integer.parseInt(idobj.get().getEnrollId().substring(5));
					idnum++;
					eid = "ENRL0" + idnum;
				} else {
					eid = "ENRL064901";
				}
				enroll.setEnrollId(eid);
				enroll.setScheId(sche.getScheId());
				enroll.setStudId(req.getSession().getAttribute("userid").toString());
				enroll.setDate(sche.getScheDate());
				enroll.setTime(sche.getTime());
				enroll.setFirstName(sche.getFirstName());
				enroll.setRoomNo(sche.getRoomNo());
				enroll.setStatus("Enrolled");
				enroll.setCourse(sche.getCourse());
				enRepo.save(enroll);
			} else
				ra.addFlashAttribute("msg", "Seats Not Available");
		} else
			ra.addFlashAttribute("msg", "You have already enrolled another class in this date");

		return "redirect:/sche/list";
	}

	@RequestMapping("/enroll/delete")
	public String enrollDelete(@RequestParam String id) {
		Enroll obj = enRepo.findById(id).get();

		Schedule sche = repo.findByScheId(obj.getScheId()).get();
		sche.setAvlSeats(sche.getAvlSeats() + 1);
		repo.save(sche);

		enRepo.delete(obj);

		return "redirect:/sche/enrolls";
	}

}
