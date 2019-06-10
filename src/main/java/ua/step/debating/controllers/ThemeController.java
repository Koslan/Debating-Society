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

	@GetMapping("/themes")
	public String getTheme(Model model) {
		model.addAttribute("themes", repoT.findAll());
		model.addAttribute("contentPage", "themes");
		return "index";
	}
	
	@GetMapping("/themes/add")
	private String getAddPublisher(@ModelAttribute Theme theme, Model model) {
		model.addAttribute("contentPage", "addThemes");
		return "index";
	}

	@PostMapping("/themes/add")
	private String addTheme(@ModelAttribute Theme theme) {
		boolean isEmty = true;
		List<Theme> themes = repoT.findAll();
		for (int i = 0; i < themes.size(); i++) {
			if (themes.get(i).getName().equals(theme.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			repoT.saveAndFlush(theme);
			return "redirect:/themes";
		} else {
			return "redirect:/themes/add";
		}
	}
	
}
