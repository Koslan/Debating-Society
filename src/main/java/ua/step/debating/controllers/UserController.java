package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repoU;

	@GetMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users", repoU.findAll());
		return "users";
	}
}
