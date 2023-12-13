package com.stud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stud.model.Slot;
import com.stud.repository.ProfessorRepository;
import com.stud.repository.SlotRepository;

@RequestMapping("/slot")
@Controller
public class SlotController {

	@Autowired
	SlotRepository repo;

	@Autowired
	ProfessorRepository profRepo;

	@RequestMapping("/list")
	public String home(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "slot";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("datalist", profRepo.findAll());
		return "slot_create";
	}

	@RequestMapping("/save")
	public String save(RedirectAttributes ra, Slot obj) {
		
		Optional<List<Slot>> slots = repo.findAllByProfIdAndDay(obj.getProfId(), obj.getDay());
		
		if(slots.get().isEmpty())
		{
		Optional<Slot> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getSlotId().substring(5));
			idnum++;
			id = "SLOT0" + idnum;
		} else {
			id = "SLOT064901";
		}
		obj.setSlotId(id);
		
		String profName = profRepo.findByProfId(obj.getProfId()).get().getFirstName();
		obj.setFirstName(profName);
		repo.save(obj);
		}
		else
			ra.addFlashAttribute("msg", "This professor already has class in this same slot");
		return "redirect:/slot/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Slot> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/slot/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		model.addAttribute("datalist", profRepo.findAll());

		return "slot_edit";
	}

	@RequestMapping("/update")
	public String update(Slot obj) {
		
		String profName = profRepo.findByProfId(obj.getProfId()).get().getFirstName();
		obj.setFirstName(profName);
		repo.save(obj);
		return "redirect:/slot/list";
	}
}
