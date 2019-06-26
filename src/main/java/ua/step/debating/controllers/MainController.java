package ua.step.debating.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.debating.models.User;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SphereRepository sphereRepository;

	@GetMapping("/")
	private String getIndex(Model model) {
		getHeader(model);
		model.addAttribute("spheres", sphereRepository.findAll());
		model.addAttribute("contentPage", "spheres");
		return "index";
	}

	@GetMapping("/inDeveloping")
	private String getInDevelopingMessage(Model model) {
		getHeader(model);
		model.addAttribute("contentPage", "inDeveloping");
		return "index";
	}

	private Integer getAuthUserId(UserRepository repo) {
		Integer id = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		if (!name.equals("anonymousUser")) {
			Optional<User> user = repo.findByLogin(name);
			id = user.get().getId();
		}
		return id;
	}

	private void getHeader(Model model) {
		Integer idUs = getAuthUserId(userRepository);
		if (idUs != null) {
			User user = userRepository.findById(idUs).orElse(new User());
			model.addAttribute("image", user.getUserImage());
			model.addAttribute("reputation", user.getStatistics().getReputation());
			model.addAttribute("activity", user.getStatistics().getActivity());
		} else {
			model.addAttribute("image", "");
			model.addAttribute("reputation", 0);
			model.addAttribute("activity", 0);
		}
	}
}
