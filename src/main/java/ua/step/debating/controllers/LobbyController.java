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
import org.springframework.web.bind.annotation.RequestParam;

import ua.step.debating.models.Configuration;
import ua.step.debating.models.Lobby;
import ua.step.debating.models.LobbyStatistics;
import ua.step.debating.models.Sphere;
import ua.step.debating.models.TalkType;
import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.models.UserStatistics;
import ua.step.debating.repositories.ConfigurationRepository;
import ua.step.debating.repositories.LobbyRepository;

import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;
import ua.step.debating.repositories.UserStatisticsRepository;
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
	private UserRepository userRepo;
	
	@Autowired
	private LobbyStatisticsRepository lobbyStatRepo;
	
	@Autowired
	private UserStatisticsRepository userStatRepo;

	//@Autowired
	//UserRepository userRepository;

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


	@PostMapping("/createDebate")
	private String addBookSubmit(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Theme") Theme theme,
			@ModelAttribute("Configuration") Configuration configuration, @ModelAttribute("Sphere") Sphere sphere, @ModelAttribute("spheresId") String sphereId,
			@ModelAttribute("subSphereId") String subSphereId) {
	
		lobby.setCreateDate(Calendar.getInstance().getTime());
		lobby.setActive(true);
		lobby.setName(theme.getName() + " /n Пользователь1" + " vs " + "Пользователь2" );
		configuration.setTalkType(TalkType.DEBATE);
		configurationRepository.saveAndFlush(configuration);
		lobby.setConfig(configuration);
		repoL.saveAndFlush(lobby);
		
		return "redirect:/themes";
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
	
	
	 
	/** Следующий метод реализует логику завершения дебатов и обновления соответствующих сущностей */
	
	@PostMapping(value = "/debateLobby", params = {"winnerReputation", "loserReputation",
													"winnerActivity", "loserActivity"})
	public String finishDebate(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Winner")
							User winner, @ModelAttribute("Loser")
							User loser, @ModelAttribute("LobbyStatisticsByWinner")
							LobbyStatistics lobbyStatisticsByWinner,
							@ModelAttribute("LobbyStatisticsByLoser")
							LobbyStatistics lobbyStatisticsByLoser, @ModelAttribute("WinnerStatistics")
							UserStatistics winnerStatistics, @ModelAttribute("LoserStatistics")
							UserStatistics loserStatistics, Integer winnerReputation,
							Integer loserReputation, Integer winnerActivity, Integer loserActivity) {
		
		lobby.setActive(false); // Данное лобби становится не активным после окончания беседы
		
		winner.getLobbies().add(lobby); 
		loser.getLobbies().add(lobby);
		
		lobbyStatisticsByWinner.getListOfLobbyDebat().add(lobby);
		lobbyStatisticsByLoser.getListOfLobbyDebat().add(lobby);
		
		winnerStatistics.setActivity(winnerReputation);
		winnerStatistics.setActivity(winnerActivity);
		
		loserStatistics.setActivity(loserReputation);
		loserStatistics.setActivity(loserActivity);
		
		repoL.saveAndFlush(lobby);
		
		userRepo.saveAndFlush(winner);
		userRepo.saveAndFlush(loser);
		
		lobbyStatRepo.saveAndFlush(lobbyStatisticsByWinner);
		lobbyStatRepo.saveAndFlush(lobbyStatisticsByLoser);
		
		userStatRepo.saveAndFlush(winnerStatistics);
		userStatRepo.saveAndFlush(loserStatistics);
		
		return "redirect:/debateLobby";
		
	}
	
}
