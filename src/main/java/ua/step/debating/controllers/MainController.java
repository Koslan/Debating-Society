package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.SphereRepository;

@Controller
public class MainController {
	@Autowired
	private SphereRepository repoS;

	@GetMapping("/")
	private String getIndex(Model model) {
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("contentPage", "fragments/header");
		model.addAttribute("contentPage", "spheres");
		return "index";
	}
}
