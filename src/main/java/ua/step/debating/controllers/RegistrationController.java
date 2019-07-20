package ua.step.debating.controllers;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.step.debating.models.LobbyStatistics;
import ua.step.debating.models.Role;
import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.models.UserStatistics;
import ua.step.debating.repositories.LobbyStatisticsRepository;
import ua.step.debating.repositories.RoleRepository;
import ua.step.debating.repositories.UserRepository;
import ua.step.debating.repositories.UserStatisticsRepository;

@Controller
public class RegistrationController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LobbyStatisticsRepository LobbyStatisticsRepository;
	@Autowired
	private UserStatisticsRepository userStatisticsRepository;

	private static final int WEAK_STRENGTH = 1;
	private static final int MIDLE_STRENGTH = 5;
	private static final int STRONG_STRENGTH = 7;
	
	private static final String WEAK_COLOR = "#FF0000";
	private static final String MIDLE_COLOR = "#FF9900";
	private static final String STRONG_COLOR = "#0099CC";
	
	/**
	 * Данный метод проверяе длину password
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/checkStrength", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
	public @ResponseBody
	String checkStrength(@RequestParam String password) {
		String result = "<span style=\"color:%s; font-weight:bold;\">%s</span>";

		if (password.length() >= WEAK_STRENGTH & password.length() < MIDLE_STRENGTH) {
			// добавить локализацию
			return String.format(result, WEAK_COLOR, "Слабый");
		} else if (password.length() >= MIDLE_STRENGTH & password.length() < STRONG_STRENGTH) {
			return String.format(result, MIDLE_COLOR, "Средний");
		} else if (password.length() >= STRONG_STRENGTH) {
			return String.format(result, STRONG_COLOR, "Сильный");
		}
		return "";
	}
	
	@GetMapping(value = "/registration")
	public String getRegistrationPage() {
		return "registration";
	}

	@PostMapping(value = "/registration")
	private String addUser(@ModelAttribute User user, @ModelAttribute("userImage") String userImage) {
		boolean isEmpty = true;

		List<User> users = userRepository.findAll();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getLogin().equals(user.getLogin())) {
				isEmpty = false;
				break;
			}
		}
		if (isEmpty) {
			List<Role> userRole = roleRepository.findAll();
			for (int i = 0; i < userRole.size(); i++) {
				if (!userRole.get(i).getRole().equals("user")) {
					userRole.remove(i);
				}
			}
			user.setRoles(userRole);
			user.setUserImage(userImage);
			user.setUserStatistics(getNewUserStatistics());
			user.setLobbyStatistics(getNewLobbyStatistics());
			userRepository.saveAndFlush(user);
			return "redirect:/login";
		}
		return "registration";
	}

	private UserStatistics getNewUserStatistics() {
		UserStatistics userStatistics = new UserStatistics();
		userStatistics.setActivity(0);
		userStatistics.setReputation(0);
		userStatistics.setSubscribers(new ArrayList<User>());
		userStatistics.setSubscriptions(new ArrayList<User>());
		userStatistics.setTopicSubscriptions(new ArrayList<Theme>());
		userStatistics.setListOfUserTopics(new ArrayList<Theme>());
		userStatisticsRepository.saveAndFlush(userStatistics);
		return userStatistics;
	}
	
	private LobbyStatistics getNewLobbyStatistics() {
		LobbyStatistics LobbyStatistics = new LobbyStatistics();
		LobbyStatisticsRepository.saveAndFlush(LobbyStatistics);
		return LobbyStatistics;
	}
}
