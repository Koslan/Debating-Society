package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.ThemeRepository;

@Controller
public class ThemeController {

	@Autowired
	private ThemeRepository repoT;

	@GetMapping("/theme")
	public String getTheme(Model model) {
		model.addAttribute("theme", repoT.findAll());
		return "theme";
	}
	
}
