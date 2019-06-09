package ua.step.debating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.debating.models.Theme;
import ua.step.debating.repositories.ThemeRepository;

@Controller
public class ThemeController {

	@Autowired
	private ThemeRepository repoT;

	@GetMapping("/theme")
	public String getTheme(Model model) {
		model.addAttribute("theme", repoT.findAll());
		model.addAttribute("contentPage", "theme");
		return "index";
	}
	
	@GetMapping("/theme/add")
	private String getAddPublisher(@ModelAttribute Theme theme, Model model) {
		model.addAttribute("contentPage", "addTheme");
		return "index";
	}

	@PostMapping("/theme/add")
	private String addTheme(@ModelAttribute Theme theme) {
		boolean isEmty = true;
		List<Theme> publishers = repoT.findAll();
		for (int i = 0; i < publishers.size(); i++) {
			if (publishers.get(i).getName().equals(theme.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			repoT.saveAndFlush(theme);
			return "redirect:/theme";
		} else {
			return "redirect:/theme/add";
		}
	}
	
}
