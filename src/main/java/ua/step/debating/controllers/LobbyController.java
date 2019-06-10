package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.LobbyRepository;
import ua.step.debating.repositories.SphereRepository;

@Controller
public class LobbyController {

	@Autowired
	private LobbyRepository repoL;
	
	@Autowired
	private SphereRepository repoS;


	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		return "lobbies";
	}
	
	@GetMapping("/createDebate")
	public String createDebate(Model model) {
		model.addAttribute("contentPage", "createDebate");
		model.addAttribute("spheres", repoS.findAll());
		return "index";
	}
}
