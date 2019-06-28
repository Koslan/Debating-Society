package ua.step.debating.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;

@Controller
public class ThemeController {
	/*
	 * 
	 * @author Bartalev, Buriak
	 *
	 */
	@Autowired
	private ThemeRepository themeRepository;
	@Autowired
	private SphereRepository sphereRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/themes")
	public String getTheme(Model model) {
		getHeader(model);
		model.addAttribute("themes", themeRepository.findAll());
		model.addAttribute("contentPage", "themes");
		return "index";
	}

	@GetMapping("/autoConnect")
	public String getAutoConnect(Model model) {
		model.addAttribute("themes", themeRepository.findAll());
		getHeader(model);
		model.addAttribute("contentPage", "themes");
		return "index";
	}

	@GetMapping("/themes/{themesId}")
	public String getThemes(Model model, @PathVariable int themesId) {
		model.addAttribute("themes", themeRepository.getOne(themesId));
		getHeader(model);
		model.addAttribute("contentPage", "themes");
		return "index";
	}

	@GetMapping("/themes/createTheme")
	private String getAddTheme(Model model) {
		getHeader(model);
		model.addAttribute("spheres", sphereRepository.findAll());
		model.addAttribute("themes", themeRepository.findAll());
		model.addAttribute("contentPage", "createTheme");
		return "index";
	}

	/**
	 * Создает новую тему. Название берет из 2 позиций
	 */
	@PostMapping("/themes/createTheme") 
	private String createTheme(@ModelAttribute Theme theme) {
		String themeName = "";
		int check = theme.getFirstPosition().compareToIgnoreCase(theme.getSecondPosition());
		if (check <= -1) {
			themeName =theme.getFirstPosition() + " vs " + theme.getSecondPosition();
		} else if (check >= 1) {
			// инверсия позиций(если название, например, "б vs а" то позиции поменяются местами)
			String position1 = theme.getFirstPosition();
			String position2 = theme.getSecondPosition();
			theme.setFirstPosition(position2);
			theme.setSecondPosition(position1);
			themeName = theme.getSecondPosition() + " vs " + theme.getFirstPosition();
		} else {
			return "redirect:/themes/theSamePositionError";
		}

		if (themeName.length() < 2) {
			return "redirect:/themes/isExistTheme";
		} else {
			for (Theme themeOne : themeRepository.findAll()) {
				if (themeOne.getName().equalsIgnoreCase(themeName)) {
					return "redirect:/themes/" + themeOne.getId();
				}
			}
			theme.setName(themeName);
		}

		theme.setCreator(userRepository.getOne(getAuthUserId(userRepository)));
		theme.setBackgroundImage("themes/" + theme.getBackgroundImage()); // по другому не работает
		themeRepository.save(theme);
		return "redirect:/themes";

	}

	@GetMapping(value = "/themes", params = { "search" })
	private String getSearchTheme(Model model, String search) {
		int count = 0;
		List<Theme> themeList = themeRepository.findAll();
		List<Theme> searchList = null;
		if (!search.isEmpty() && count == 0) {
			searchList = new ArrayList<Theme>();
			for (int i = 0; i < themeList.size(); i++) {
				if (themeList.get(i).getName().toLowerCase().contains(search.toLowerCase())) {
					searchList.add(themeList.get(i));
					count++;
				}
			}
			model.addAttribute("themes", searchList);
			model.addAttribute("contentPage", "themes");
		}
		if (search.isEmpty() || count == 0) {
			model.addAttribute("themes", searchList);
			model.addAttribute("searchRequest", search);
			model.addAttribute("contentPage", "/fragments/invalidRequest");
		}
		getHeader(model);
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
