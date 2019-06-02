package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.LobbyStatisticsRepository;

@Controller
public class LobbyStatisticsController {

	@Autowired
	private LobbyStatisticsRepository repoLS;

	@GetMapping("/lobbyStatistics")
	public String getConfigurations(Model model) {
		model.addAttribute("lobbyStatistics", repoLS.findAll());
		return "lobbyStatistics";
	}
}
