package ua.step.debating.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.debating.models.Theme;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;

@Controller
public class ThemeController {

	/*
	 * 
	 * @author Bartalev
	 *
	 */
	@Autowired
	private ThemeRepository repoT;
	private SphereRepository repoS;

	@GetMapping("/themes")
	public String getTheme(Model model) {
		model.addAttribute("themes", repoT.findAll());
		model.addAttribute("contentPage", "themes");
		return "index";
	}
	
	@GetMapping("/autoConnect")
	public String getAutoConnect(Model model) {
		model.addAttribute("themes", repoT.findAll());
		//model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("contentPage", "themes");
		return "index";
	}
	
	@GetMapping("/themes/{themesId}")
	public String getThemes(Model model, @PathVariable int themesId) {
		model.addAttribute("themes", repoT.findAll());
		//model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("themesId", themesId);
		model.addAttribute("contentPage", "themes");
		return "index";
	}

	@GetMapping("/themes/add")
	private String getAddTheme(Model model) {
		model.addAttribute("contentPage", "addThemes");
		return "index";
	}

	@PostMapping("/themes/add") // добавление несуществующей темы, если она уже есть идет перенаправление
								// на новый ввод темы, иначе она добавляется и идет обновление текущей
								// страницы themes
	private String addTheme(@ModelAttribute Theme theme) {
		boolean isEmpty = true;
		List<Theme> themes = repoT.findAll();
		for (int i = 0; i < themes.size(); i++) {
			if (themes.get(i).getName().equals(theme.getName())) {
				isEmpty = false;
			}
		}
		if (isEmpty) {
			repoT.saveAndFlush(theme);
			return "redirect:/themes";
		} else {
			return "redirect:/themes/add";
		}
	}
	
	@GetMapping(value = "/themes", params = { "search" })
	private String getSearchTheme(Model model, String search) {
		int count = 0;
		List<Theme> themeList = repoT.findAll();
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
		return "index";
	}

}
