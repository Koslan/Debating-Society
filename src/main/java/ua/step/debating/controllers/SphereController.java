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

import ua.step.debating.models.Sphere;
import ua.step.debating.models.Theme;
import ua.step.debating.models.User;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;
import ua.step.debating.repositories.UserRepository;

/**
 * 
 * @author Vitaly
 *
 */

@Controller
public class SphereController {
	@Autowired
	private SphereRepository sphereRepository;

	@Autowired
	private ThemeRepository themeRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/spheres")
	public String getSpheres(Model model) {
		getHeader(model);
		model.addAttribute("spheres", sphereRepository.findAll());
		model.addAttribute("contentPage", "spheres");
		return "index";
	}

	@GetMapping("/spheres/{spheresId}")
	public String getThemes(Model model, @PathVariable int spheresId) {
		if (spheresId > 99) {
			List<Theme> themes = themeRepository.findAll();
			List<Sphere> spheres = sphereRepository.findAll();
			List<Theme> chooseThemes = new ArrayList<Theme>();
			List<Theme> themesTempary = new ArrayList<Theme>();

			for (Sphere sphere : spheres) {
				if (sphere.getId().equals(spheresId)) {
					themesTempary = sphere.getThemes();
					System.out.println(themesTempary.size());
				}
			}
			model.addAttribute("themes", themeRepository.findAll());
			model.addAttribute("spheres", sphereRepository.findAll());
			model.addAttribute("spheresId", spheresId);
			model.addAttribute("themesTempary", themesTempary);
			model.addAttribute("contentPage", "subthemes");
		}

		else {
			model.addAttribute("spheres", sphereRepository.findAll());
			model.addAttribute("spheresId", spheresId);
			model.addAttribute("contentPage", "subspheres");
		}
		getHeader(model);
		return "index";
	}

	@GetMapping(value = "/spheres/{spheresId}", params = { "search" })
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

	@GetMapping("/createSphere")
	private String getAddTheme(Model model) {
		getHeader(model);
		model.addAttribute("spheres", sphereRepository.findAll());
		model.addAttribute("sphereName", "нет имени");
		model.addAttribute("contentPage", "createSphere");
		return "index";
	}

	/**
	 * Create new sphere
	 */
	@PostMapping("/createSphere")
	private String createTheme(@ModelAttribute("sphere") Sphere sphere,
			@ModelAttribute("sphereName") String sphereName) {
		Sphere subSphere = new Sphere();
		if (sphere == null) {
			subSphere.setId(getEmptySphereId());
		} else {
			subSphere.setParent(sphere);
		}
		subSphere.setName(sphereName);

		System.out.println("!!!!!!!!!!!!!!" + subSphere.getName());
		System.out.println("!!!sssss!!!!!!" + getEmptySphereId());

		sphereRepository.saveAndFlush(subSphere);
		return "redirect:/spheres";
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
			model.addAttribute("reputation", user.getUserStatistics().getReputation());
			model.addAttribute("activity", user.getUserStatistics().getActivity());
		} else {
			model.addAttribute("image", "");
			model.addAttribute("reputation", 0);
			model.addAttribute("activity", 0);
		}
	}

	private Integer getEmptySphereId() {

		for (int i = 1; i < 100; i++) {
			if (!sphereRepository.existsById(i)) {
				return i;
			}
		}
		return -1;
	}

}
