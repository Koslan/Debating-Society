package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.SphereRepository;

@Controller
public class SphereController {

	@Autowired
	private SphereRepository repoS;

	@GetMapping("/spheres")
	public String getSpheres(Model model) {
		model.addAttribute("spheres", repoS.findAll());
		return "spheres";
	}
	
}
