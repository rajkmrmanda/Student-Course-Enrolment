package com.stud.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stud.model.Login;
import com.stud.model.Professor;
import com.stud.repository.CourseRepository;
import com.stud.repository.ProfessorRepository;

@RequestMapping("/professor")
@Controller
public class ProfessorController {

	@Autowired
	ProfessorRepository repo;

	@Autowired
	CourseRepository couRepo;

	@RequestMapping()
	public String login(Model model) {
		return "prof_login";
	}

	@RequestMapping("index")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest req) {
		return "home";
	}

	@RequestMapping("/reset")
	public String resetPage(Model model, HttpServletRequest req) {
		return "prof_reset";
	}

	@PostMapping("/reset/update")
	public String reset(RedirectAttributes ra, @RequestParam String email, @RequestParam String curpass, @RequestParam String newpass,
			Model model, HttpServletRequest request) {
		Optional<Professor> obj = repo.findByEmailAndPassword(email, curpass);
		if (obj.isPresent()) {
			Professor prof = obj.get();
			prof.setPassword(newpass);
			repo.save(prof);
			return "redirect:/professor";

		} else {
			ra.addFlashAttribute("msg", "Invalid Password Reset Credentials");
			return "redirect:/professor/reset";
		}
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest req) {
		req.getSession().invalidate();
		return "logout";
	}

	@PostMapping("/login")
	public String show(Login login, Model model, HttpServletRequest request) {
		Optional<Professor> obj = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (obj.isPresent()) {
			if (obj.get().getPassword().equals("prof")) {
				return "redirect:/professor/reset";
			} else {
				model.addAttribute("name", obj.get().getFirstName());
				request.getSession().setAttribute("id", obj.get().getId());
				request.getSession().setAttribute("userid", obj.get().getProfId());
				request.getSession().setAttribute("usertype", "professor");
				request.getSession().setAttribute("name", obj.get().getFirstName());
				return "redirect:/professor/home";
			}
		} else {
			model.addAttribute("msg", "Invalid Login Credentials");
			return "prof_login";
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "prof";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("courses", couRepo.findAll());
		return "prof_create";
	}

	@RequestMapping("/save")
	public String save(Professor obj) {
		Optional<Professor> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getProfId().substring(5));
			idnum++;
			id = "PROF0" + idnum;
		} else {
			id = "PROF064901";
		}

		obj.setProfId(id);
		obj.setPassword("prof");
		repo.save(obj);
		return "redirect:/professor/list";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "prof_show";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Professor> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/professor/list";
	}

	@RequestMapping("/edit")
	public String edit(Model model, HttpServletRequest req) {
		model.addAttribute("obj", repo.findByProfId(req.getSession().getAttribute("userid").toString()).get());
		model.addAttribute("courses", couRepo.findAll());

		return "prof_edit";
	}

	@RequestMapping("/update")
	public String update(Professor obj) {
		repo.save(obj);
		return "redirect:/professor/home/";
	}
}
