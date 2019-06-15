package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.step.debating.repositories.LobbyRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;

@Controller
public class LobbyController {

	@Autowired
	private LobbyRepository repoL;
	
	@Autowired
	private SphereRepository repoS;
	
	@Autowired
	private ThemeRepository repoT;

	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		return "lobbies";
	}
	
	@GetMapping("/debateAutoConnect")
	public String getOpponent(Model model) {
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("themes", repoT.findAll());
		return "debateAutoConnect";
	}
	
	@GetMapping("/timer")
	public String getTimer(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		model.addAttribute("contentPage", "timer");
		return "timer";
	}
}
