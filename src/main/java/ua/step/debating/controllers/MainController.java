package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.ThemeRepository;

@Controller
public class MainController {
	@Autowired
	private ThemeRepository themeRepositary;

	@GetMapping("/")
	private String getIndex(Model model) {
		model.addAttribute("contentPage", "fragments/themes");
		return "index";
	}
}
