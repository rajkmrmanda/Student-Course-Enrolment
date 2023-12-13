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
import com.stud.model.Student;
import com.stud.repository.StudentRepository;

@RequestMapping("/student")
@Controller
public class StudentController {

	@Autowired
	StudentRepository repo;

	@RequestMapping()
	public String login(Model model) {
		return "stud_login";
	}

	@RequestMapping("index")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/home")
	public String home(Model model, HttpServletRequest req) {
		return "home";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpServletRequest req) {
		req.getSession().invalidate();
		return "logout";
	}

	@PostMapping("/login")
	public String show(RedirectAttributes ra, Login login, Model model, HttpServletRequest request) {
		Optional<Student> obj = repo.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if (obj.isPresent()) {
			if (obj.get().getStatus().equals("Approved")) {
				model.addAttribute("name", obj.get().getName());
				request.getSession().setAttribute("id", obj.get().getId());
				request.getSession().setAttribute("userid", obj.get().getStudId());
				request.getSession().setAttribute("usertype", "student");
				request.getSession().setAttribute("name", obj.get().getName());
				return "redirect:/student/home";
			} else {
				 ra.addFlashAttribute("msg", "Your Account is not yet Approved");
				return "redirect:/student";

			}
		} else {
			model.addAttribute("msg", "Invalid Login Credentials");
			return "stud_login";
		}
	}

	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("datalist", repo.findAll());
		return "stud";
	}

	@RequestMapping("/create")
	public String create(Model model) {
		return "stud_create";
	}

	@RequestMapping("/save")
	public String save(Student obj) {
		Optional<Student> idobj = repo.findTopByOrderByIdDesc();
		String id = null;
		if (idobj.isPresent()) {
			int idnum = Integer.parseInt(idobj.get().getStudId().substring(5));
			idnum++;
			id = "STUD0" + idnum;
		} else {
			id = "STUD064901";
		}

		obj.setStudId(id);
		obj.setStatus("UnApproved");
		repo.save(obj);
		return "redirect:/student";
	}

	@RequestMapping("/show/{id}")
	public String show(@PathVariable String id, Model model) {
		model.addAttribute("obj", repo.findById(id).get());
		return "stud_show";
	}
	@RequestMapping("/approve/{id}")
	public String approve(@PathVariable String id, Model model) {
		Student stud = repo.findById(id).get();
		stud.setStatus("Approved");
		repo.save(stud);
		return "redirect:/student/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam String id) {
		Optional<Student> obj = repo.findById(id);
		repo.delete(obj.get());

		return "redirect:/student/list";
	}

	@RequestMapping("/edit")
	public String edit(Model model, HttpServletRequest req) {
		model.addAttribute("obj", repo.findByStudId(req.getSession().getAttribute("userid").toString()).get());
		return "stud_edit";
	}

	@RequestMapping("/update")
	public String update(Student obj) {
		repo.save(obj);
		return "redirect:/student/home/";
	}
}
