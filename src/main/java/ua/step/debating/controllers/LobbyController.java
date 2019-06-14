package ua.step.debating.controllers;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.debating.models.Configuration;
import ua.step.debating.models.Lobby;
import ua.step.debating.models.LobbyStatistics;
import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.repositories.ConfigurationRepository;
import ua.step.debating.repositories.LobbyRepository;
import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;

/**
 * 
 * @author Константин
 *
 */
@Controller
public class LobbyController {

	@Autowired
	private LobbyRepository repoL;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private SphereRepository repoS;
	
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired UserRepository userRepository;


	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		return "lobbies";
	}
	
	@GetMapping("/createDebate")
	public String createDebate(Model model) {
		model.addAttribute("contentPage", "createDebate");
		model.addAttribute("spheres", repoS.findAll());
		return "index";
	}
	
	@PostMapping("/createDebate")
	private String addBookSubmit(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Configuration") Configuration configuration, @ModelAttribute("Theme") Theme theme) {
		Integer idUs = 1;
		User user =  getAuthUserId(idUs);
		lobby.setCreateDate(Calendar.getInstance().getTime());
		lobby.setActive(true);
		configurationRepository.saveAndFlush(configuration);
		theme.setFirstPosition("1");
		theme.setSecondPosition("1");
		theme.setCreator(user);
		themeRepository.saveAndFlush(theme);
		
		lobby.setConfig(configuration);
		lobby.setTheme(theme);
		repoL.saveAndFlush(lobby);
		
		return "redirect:/themes";
	}
	
	
	private User getAuthUserId(Integer idUs) {
		for (User user : userRepository.findAll()) {
			if(user.getId().equals(idUs)) {
				return user;
			}
		}


		return null;
	}
}
