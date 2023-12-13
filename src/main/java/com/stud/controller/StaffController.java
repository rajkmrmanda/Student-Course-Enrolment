package com.stud.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stud.model.Login;

@RequestMapping("/admin")
@Controller
public class StaffController {

	@RequestMapping()
	public String login(Model model) {
		return "admin_login";
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
	public String show(Login login, Model model, HttpServletRequest request) {
				if (login.getEmail().equals("admin") && login.getPassword().equals("admin")) {
				model.addAttribute("name", "Admin");
				request.getSession().setAttribute("id", "Admin");
				request.getSession().setAttribute("userid", "Admin");
				request.getSession().setAttribute("usertype", "admin");
				request.getSession().setAttribute("name","Admin");
				return "redirect:/admin/home";
			
		} else {
			model.addAttribute("msg", "Invalid Login Credentials");
			return "admin_login";
		}
	}


}
