package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.UserStatisticsRepository;

@Controller
public class UserStatisticsController {

	@Autowired
	private UserStatisticsRepository repoUS;

	@GetMapping("/userStatistics")
	public String getUserStatistics(Model model) {
		model.addAttribute("userStatistics", repoUS.findAll());
		return "userStatistics";
	}
}
