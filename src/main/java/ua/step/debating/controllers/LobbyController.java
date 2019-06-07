package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.LobbyRepository;

@Controller
public class LobbyController {

	@Autowired
	private LobbyRepository repoL;

	@GetMapping("/lobbies")
	public String getConfigurations(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		return "lobbies";
	}
}
