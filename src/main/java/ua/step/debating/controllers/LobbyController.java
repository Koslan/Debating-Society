package ua.step.debating.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

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
	
	String toTimerTwo = null;

	@GetMapping("/lobbies")
	public String getLobbies(Model model) {
		model.addAttribute("lobbies", lobbyRepository.findAll());
		getHeader(model);
		return "lobbies";
	}

	/**
	 * @author Vitaliy
	 * @param model
	 * @param themesId
	 * @return Данный метод используется для вывода списка активных lobby
	 * 
	 */
	@GetMapping("/lobbies/{themesId}")
	public String getLobbiesByTheme(Model model, @PathVariable int themesId) {
		List<Lobby> lobbiesRepository = lobbyRepository.findAll();
		List<Lobby> lobbiesByTheme = new ArrayList<Lobby>();
		for (Lobby lobby : lobbiesRepository) {
			if (lobby.getTheme().getId() == themesId) {
				lobbiesByTheme.add(lobby);
			}
		}
		model.addAttribute("lobbies", lobbiesByTheme);
		model.addAttribute("themes", themeRepository.getOne(themesId));
		model.addAttribute("contentPage", "lobbies");
		getHeader(model);
		return "index";
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

		lobby.setCreateDate(LocalDateTime.now());
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
	 * @author Константин
	 */
	@GetMapping("/configureDebate/{id}")
	private String configureDebate(@PathVariable("id") Integer id, Model model) {
		Lobby lobby = lobbyRepository.getOne(id);
		model.addAttribute("contentPage", "configureDebate");
		model.addAttribute("configuration", lobby.getConfig());
		model.addAttribute("subsphere", lobby.getTheme().getSphere().getName());
		model.addAttribute("sphere", lobby.getTheme().getSphere().getParent().getName());
		model.addAttribute("lobby", lobby);
		model.addAttribute("theme", lobby.getTheme().getName());
		getHeader(model);
		return "index";
	}

	@PostMapping("/configureDebate/{id}")
	private String configureDebateSubmit(@PathVariable("id") Integer id, @ModelAttribute("Lobby") Lobby lobby,
			@ModelAttribute("Configuration") Configuration configuration) {
		configurationRepository.saveAndFlush(configuration);
		return "redirect:/configureDebate/" + id;
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
				if (lobby.getTheme().getFirstPosition().equalsIgnoreCase(position)) {
					if (lobby.getFirstSide().size() == 0 && lobby.getSecondSide().size() > 0) {
						side = 1;
						loginOponent = lobby.getSecondSide();

						lobby.getFirstSide().add(userRepository.getOne(getAuthUserId()));
						lobbyRepository.saveAndFlush(lobby);
						lobbies.add(lobby);
						break;
					}
				}

				if (lobby.getTheme().getSecondPosition().equalsIgnoreCase(position)) {
					if (lobby.getFirstSide().size() > 0) {
						side = 2;
						loginOponent = lobby.getFirstSide();

						lobby.getSecondSide().add(userRepository.getOne(getAuthUserId()));
						System.out.println("two" + lobby.toString());
						lobbies.add(lobby);
						lobbyRepository.saveAndFlush(lobby);
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

	@GetMapping("/debateLobby/{debateId}")
	public String getDebateChat(Model model, @PathVariable int debateId) {
		Lobby lobby = lobbyRepository.findById(debateId).get();
		User firstSideUser = lobby.getFirstSide().get(0);
		User secondSideUser = lobby.getSecondSide().get(0);
		model.addAttribute("firstSideUser", firstSideUser);
		model.addAttribute("secondSideUser", secondSideUser);
		model.addAttribute("debate", lobby);
		model.addAttribute("theme", lobby.getTheme());
		model.addAttribute("contentPage", "debateLobby");
		getHeader(model);
		return "index";
	}
	
	/**
	 * @author Vitaliy
	 * @param model
	 * @param themesId
	 * @return Данный метод используется для работы лобби
	 * 
	 */
	/*@GetMapping("/debateLobby/{debateId}")
	public String getDebateChat(Model model, @PathVariable int debateId) throws InterruptedException {
		 String dateOfDebate = "Jul 10, 2019 20:43:25"; 
		// String toTimerOne = "Jul 10, 2019 20:43:25";
		String toTimerOne = null;
		//String toTimerTwo = null;

		Boolean debateStart = false;
		Boolean debateGoing = false;
		Boolean debateFinish = false;

		Boolean isFirst = false;
		
		Boolean timeIsComplete = true;
		
		Integer pointFirstSide = 3;
		Integer pointSecondSide = 3;

		//long timeOut;

		// String endDate = null;

		Lobby lobby = lobbyRepository.findById(debateId).get();
		User user = lobby.getFirstSide().get(0);
		User user1 = lobby.getSecondSide().get(0);
		// данное lobby активное, пока не истечет дата или не присоедениться опонент
		LocalDateTime dateOfDebate = lobby.getDateOfDebate();
		// дата начала дебат
		LocalDateTime timeToStartDebate = lobby.getTimeToStartDebate();
		// дата завершения дебат
		LocalDateTime timeToFinishDebate = lobby.getTimeToFinishDebate();

		// timeOut = lobby.getConfig().getTimeoutTime();

		// текущее время
		LocalDateTime todayDateTime = LocalDateTime.now();
		
		 * LocalDateTime localDateTime = LocalDateTime.of(2015, Month.JANUARY, 25, 6,
		 * 30);
		 
		// System.out.println("текущее время " + LocalDateTime.now());

		// форматирование времени
		String todayDateTimeFormate = todayDateTime
				.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK));
		
		 * System.out.println("форматирование времени " + todayDateTime
		 * .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
		 * .withLocale(Locale.UK)));
		 

		//System.out.println("результат" + todayDateTimeFormate);

		// передача даты в верхний таймер
		// toTimerOne = todayDateTimeFormate;
		toTimerOne = dateOfDebate
				.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(Locale.UK));
		System.out.println("toTimerOne" + toTimerOne);
		
		
		// колличество минут на одно сообщение
		LocalDateTime timeToMessage = todayDateTime.plusMinutes(lobby.getConfig().getTimeoutTime());

		// передача даты в нижний таймер
		toTimerTwo = timeToMessage
				.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
				.withLocale(Locale.UK));
		System.out.println("toTimerTwo" + toTimerTwo);
		
		givenUsingTimer_whenSchedulingRepeatedTask_thenCorrect(timeToMessage, 5000L, 1000L);
		System.out.println("toTimerTwo" + toTimerTwo);
		
		if(!toTimerTwo.equals(null)) {
		
		}
		
		//получаем разницу между двумя датами
				long thirty = Duration.between(timeToMessage, todayDateTime).getSeconds();
				System.out.println("Duration" + thirty);
		long thirty=1;
		while(true && thirty != 0 ){
			Thread.sleep(1000);
			todayDateTime = LocalDateTime.now();
			thirty = Duration.between(timeToMessage, todayDateTime).getSeconds();
			System.out.println("Duration" + thirty);
			if (timeIsComplete) {
				if (isFirst) {
					pointFirstSide -= 1;
					if (pointFirstSide == 0) {
						debateGoing = false;
						debateFinish = true;
					}
				}
			} else {
				pointSecondSide -= 1;
				if (pointSecondSide == 0) {
					debateGoing = false;
					debateFinish = true;
				}
			}
		}
		
			if(debateFinish) {
		//добавить балл победителю
		debatCount+=1;
		//вычесть баллы у проигравщего
		debatCount-=1;
	}
		// System.out.println(toTimerTwo);

		model.addAttribute("toTimerOne", toTimerOne);
		model.addAttribute("toTimerTwo", toTimerTwo);
		model.addAttribute("debateFinish", debateFinish);
		model.addAttribute("firstSideUser", user);
		model.addAttribute("secondSideUser", user1);
		model.addAttribute("debate", lobby);
		model.addAttribute("theme", lobby.getTheme());
		model.addAttribute("contentPage", "debateLobby");
		getHeader(model);
		return "index";
	}*/
	
	private String startToTimerTwo(String date) {
		return date;
	}
	
	public void givenUsingTimer_whenSchedulingRepeatedTask_thenCorrect(LocalDateTime timeToMessage, long delay, long period){
		//TimerTask - задача, которую нужно выполнить, а Timer - планировщик .
		TimerTask repeatedTask = new TimerTask() {
			public void run() {
				//LocalDateTime todayDateTime = LocalDateTime.now();
				String todayDateTimeFormate = timeToMessage
						.format(DateTimeFormatter
						.ofLocalizedDateTime(FormatStyle.MEDIUM)
						.withLocale(Locale.UK));
				
				toTimerTwo = startToTimerTwo (todayDateTimeFormate);
				//System.out.println("Task performed on " + timeToMessage);
				//System.out.println("next" + startToTimerTwo(todayDateTimeFormate));
			}
		};
		Timer timer = new Timer("Timer");
		//scheduleAtFixedRate (repeatTask, delay, period) -
		//который планирует задачу для повторного выполнения 
		//с фиксированной скоростью, начиная с указанной задержки.
		timer.scheduleAtFixedRate(repeatedTask, delay, period);
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
