package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.debating.models.Vote;
import ua.step.debating.repositories.VoteRepository;

@Controller
public class VoteController {

	@Autowired
	private VoteRepository repoV;

	@GetMapping("/votes")
	public String getConfigurations(Model model) {
		model.addAttribute("votes", repoV.findAll());
		return "votes";
	}
	
	@PostMapping("/debateLobby")
	private String addVote(@ModelAttribute("Vote") Vote vote) {
			repoV.saveAndFlush(vote);
			return "redirect:/debateLobby";
		
	}
	
}
