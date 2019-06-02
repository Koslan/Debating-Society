package ua.step.debating.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.repositories.MessageRepository;

@Controller
public class MessageController {

	@Autowired
	private MessageRepository repoM;

	@GetMapping("/message")
	public String getMessage(Model model) {
		model.addAttribute("message", repoM.findAll());
		return "message";
	}
	
}
