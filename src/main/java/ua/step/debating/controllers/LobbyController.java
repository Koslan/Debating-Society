package ua.step.debating.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import ua.step.debating.models.UserStatistics;
import ua.step.debating.repositories.ConfigurationRepository;
import ua.step.debating.repositories.LobbyRepository;

import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;
import ua.step.debating.repositories.UserStatisticsRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author Константин
 *
 */
@Controller
public class LobbyController {
	@Autowired
	private LobbyRepository lobbyRepository;

	@Autowired
	private ConfigurationRepository configurationRepository;

	@Autowired
	private SphereRepository sphereRepository;

	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LobbyStatisticsRepository lobbyStatisticsRepository;

	@Autowired
	private UserStatisticsRepository userStatisticsRepository;

	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", lobbyRepository.findAll());
		getHeader(model);
		return "lobbies";
	}

	@GetMapping("/createDebate")
	public String createDebate(Model model) {
		model.addAttribute("contentPage", "createDebate");
		model.addAttribute("spheres", sphereRepository.findAll());
		model.addAttribute("theme", themeRepository.findAll());
		getHeader(model);
		return "index";
	}

	@PostMapping("/createDebate")
	private String addBookSubmit(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Theme") Theme theme,
			@ModelAttribute("Configuration") Configuration configuration, @ModelAttribute("Sphere") Sphere sphere,
			@ModelAttribute("spheresId") String sphereId, @ModelAttribute("subSphereId") String subSphereId,
			@ModelAttribute("User") User user, @ModelAttribute("userPosition") String userPosition) {

		lobby.setCreateDate(Calendar.getInstance().getTime());
		lobby.setActive(true);
		lobby.setName(theme.getName() + " /n Пользователь1" + " vs " + "Пользователь2");

		// Добавление пользователя в на ту сторону которую он выбрал
		List<User> userSide = new ArrayList<User>();
		userSide.add(userRepository.getOne(getAuthUserId()));
		if (userPosition.equals("position2")) {
			lobby.setSecondSide(userSide);
		} else {
			lobby.setFirstSide(userSide);
		}

		configuration.setTalkType(TalkType.DEBATE);
		configurationRepository.saveAndFlush(configuration);
		lobby.setConfig(configuration);
		lobbyRepository.saveAndFlush(lobby);

		return "redirect:/themes";
	}

	/**
	 * @author Vitaliy
	 * @param model
	 * @param themesId
	 * @return Данный метод используется при поиске оппонента
	 * 
	 */
	@GetMapping("/debateAutoConnect/{themesId}/{position}")
	public String getOpponent(Model model, @PathVariable int themesId, @PathVariable String position) {
		List<Lobby> lobbiesRepository = lobbyRepository.findAll();
		List<Lobby> lobbies = new ArrayList<Lobby>();
		List<User> loginOponent = new ArrayList<User>();
		int side = 0;

		for (Lobby lobby : lobbiesRepository) {
			if (lobby.getActive() && lobby.getTheme().getId().equals(themesId)) {
				System.out.println("one" + lobby.toString());
				if (lobby.getTheme().getFirstPosition().equalsIgnoreCase(position)) {
					if (lobby.getFirstSide().size() == 0 && lobby.getSecondSide().size() > 0) {
						side = 1;
						loginOponent = lobby.getSecondSide();
						lobbies.add(lobby);
						;
						break;
					}
				}

				if (lobby.getTheme().getSecondPosition().equalsIgnoreCase(position)) {
					System.out.println("two" + lobby.toString());
					if (lobby.getSecondSide().size() == 0 && lobby.getFirstSide().size() > 0) {
						side = 2;
						loginOponent = lobby.getFirstSide();
						lobbies.add(lobby);
						System.out.println("two" + lobby.toString());
						break;
					}
				}
			}
		}

		if (side > 0) {
			model.addAttribute("configuration", lobbies.get(0).getConfig());
			model.addAttribute("spheres", sphereRepository.findAll());
			model.addAttribute("lobby", lobbies.get(0));
			model.addAttribute("themes", themeRepository.getOne(themesId));
			model.addAttribute("themesId", themesId);
			model.addAttribute("position", position);
			model.addAttribute("loginOponent", loginOponent);
			model.addAttribute("contentPage", "debateAutoConnect");
		} else {
			model.addAttribute("themes", themeRepository.getOne(themesId));
			model.addAttribute("themesId", themesId);
			model.addAttribute("position", position);
			model.addAttribute("contentPage", "fragments/invalidRequestAutoConnect");
		}
		getHeader(model);
		return "index";
	}

	@GetMapping("/timer")
	public String getTimer(Model model) {
		model.addAttribute("lobbies", lobbyRepository.findAll());
		model.addAttribute("contentPage", "timer");
		getHeader(model);
		return "timer";
	}

	@GetMapping("/debateLobby")
	public String getDebateChat(Model model) {
		model.addAttribute("contentPage", "debateLobby");
		getHeader(model);
		return "index";
	}

	/**
	 * Следующий метод реализует логику завершения дебатов и обновления
	 * соответствующих сущностей
	 */

	@PostMapping(value = "/debateLobby", params = { "winnerReputation", "loserReputation", "winnerActivity",
			"loserActivity" })
	public String finishDebate(@ModelAttribute("Lobby") Lobby lobby, @ModelAttribute("Winner") User winner,
			@ModelAttribute("Loser") User loser,
			@ModelAttribute("LobbyStatisticsByWinner") LobbyStatistics lobbyStatisticsByWinner,
			@ModelAttribute("LobbyStatisticsByLoser") LobbyStatistics lobbyStatisticsByLoser,
			@ModelAttribute("WinnerStatistics") UserStatistics winnerStatistics,
			@ModelAttribute("LoserStatistics") UserStatistics loserStatistics, Integer winnerReputation,
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

		lobbyRepository.saveAndFlush(lobby);

		userRepository.saveAndFlush(winner);
		userRepository.saveAndFlush(loser);

		lobbyStatisticsRepository.saveAndFlush(lobbyStatisticsByWinner);
		lobbyStatisticsRepository.saveAndFlush(lobbyStatisticsByLoser);

		userStatisticsRepository.saveAndFlush(winnerStatistics);
		userStatisticsRepository.saveAndFlush(loserStatistics);

		return "redirect:/debateLobby";
	}

	private Integer getAuthUserId() {
		Integer id = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		if (!name.equals("anonymousUser")) {
			Optional<User> user = userRepository.findByLogin(name);
			id = user.get().getId();
		}
		return id;
	}

	private void getHeader(Model model) {
		Integer idUs = getAuthUserId();
		if (idUs != null) {
			User user = userRepository.findById(idUs).orElse(new User());
			model.addAttribute("image", user.getUserImage());
			model.addAttribute("reputation", user.getUserStatistics().getReputation());
			model.addAttribute("activity", user.getUserStatistics().getActivity());
		} else {
			model.addAttribute("image", "");
			model.addAttribute("reputation", 0);
			model.addAttribute("activity", 0);
		}
	}

}
