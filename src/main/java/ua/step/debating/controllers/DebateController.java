package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.DebateRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;



/**
 * 
 * @author vitaly
 *
 */
@Controller
public class DebateController {
	@Autowired
	private DebateRepository debRepo;
	@Autowired
	private ThemeRepository themeRepo;
	@Autowired
	private SphereRepository  sphereRepo;
	
	
/*	@GetMapping("/createDebate")
	private String getDebates(Model model) {
		model.addAttribute("debates", debRepo.findAll());
		//model.addAttribute("theme", themeRepo.findAll());
		model.addAttribute("contentPage", "theme");
		return "index";
	}*/
	/*@GetMapping("/createDebate")
	public String getTheme(Model model) {
		model.addAttribute("theme", themeRepo.findAll());
		model.addAttribute("contentPage", "theme");
		return "index";
	}*/
	@GetMapping("/createDebate")
	public String getSphere(Model model) {
		model.addAttribute("sphere", sphereRepo.findAll());
		model.addAttribute("contentPage", "sphere");
		return "index";
	}
}

	