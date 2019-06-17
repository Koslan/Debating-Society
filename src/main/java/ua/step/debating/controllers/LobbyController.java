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
import ua.step.debating.models.Sphere;
import ua.step.debating.models.TalkType;
import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.repositories.ConfigurationRepository;
import ua.step.debating.repositories.LobbyRepository;

import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;

import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;

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

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ThemeRepository repoT;

	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		return "lobbies";
	}

	@GetMapping("/createDebate")
	public String createDebate(Model model) {
		model.addAttribute("contentPage", "createDebate");
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("theme", themeRepository.findAll());
		return "index";
	}

	/////////////////////////////
	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/register") public
	 * Map<String, String> register(Model uiModel,
	 * 
	 * @RequestParam String username,
	 * 
	 * @RequestParam String password,
	 * 
	 * @RequestParam boolean auth, HttpServletRequest httpServletRequest)
	 */

	@PostMapping("/createDebate")
	private String addBookSubmit(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Theme") Theme theme,
			@ModelAttribute("Configuration") Configuration configuration, @ModelAttribute("Sphere") Sphere sphere) {

		lobby.setCreateDate(Calendar.getInstance().getTime());
		lobby.setActive(true);
		configuration.setTalkType(TalkType.DEBATE);
		configurationRepository.saveAndFlush(configuration);
		lobby.setConfig(configuration);
		repoL.saveAndFlush(lobby);

		return "redirect:/themes";
	}

	private User getAuthUserId(Integer idUs) {
		for (User user : userRepository.findAll()) {
			if (user.getId().equals(idUs)) {
				return user;
			}
		}
		return null;
	}

	@GetMapping("/debateAutoConnect")
	public String getOpponent(Model model) {
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("themes", repoT.findAll());
		return "debateAutoConnect";
	}

	@GetMapping("/timer")
	public String getTimer(Model model) {
		model.addAttribute("lobbies", repoL.findAll());
		model.addAttribute("contentPage", "timer");
		return "timer";
	}
	
	@GetMapping("/debateLobby")
	public String getDebateChat(Model model) {
		model.addAttribute("contentPage", "debateLobby");
		return "index";
	}
	
}
