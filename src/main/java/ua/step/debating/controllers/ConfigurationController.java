package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.ConfigurationRepository;

@Controller
public class ConfigurationController {

	@Autowired
	private ConfigurationRepository repoC;

	@GetMapping("/configurations")
	public String getConfigurations(Model model) {
		model.addAttribute("configurations", repoC.findAll());
		return "configurations";
	}
	
	
}
