package ua.step.debating.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.step.debating.models.Sphere;
import ua.step.debating.models.Theme;
import ua.step.debating.repositories.SphereRepository;
import ua.step.debating.repositories.ThemeRepository;

@Controller
public class SphereController {

	@Autowired
	private SphereRepository repoS;
	
	@Autowired
	private ThemeRepository repoT;

	@GetMapping("/spheres")
	public String getSpheres(Model model) {
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("contentPage", "spheres");
		return "index";
	}

	@GetMapping("/spheres/{spheresId}")
	public String getThemes(Model model, @PathVariable int spheresId) {
		if (spheresId > 99) {
			List<Theme> themes = repoT.findAll();
			List<Sphere> spheres = repoS.findAll();
			List<Theme> chooseThemes = new ArrayList<Theme>();
			List<Theme> themesTempary = new ArrayList<Theme>();

			for (Sphere sphere : spheres) {
			if (sphere.getId().equals(spheresId)) {
					themesTempary = sphere.getThemes();
					System.out.println(themesTempary.size());
					/*for (Theme theme : themesTempary) {
						chooseThemes.add(theme);
					}*/
				}
			}
			model.addAttribute("themes", repoT.findAll());
			model.addAttribute("spheres", repoS.findAll());
			model.addAttribute("spheresId", spheresId);
			model.addAttribute("themesTempary", themesTempary);
			model.addAttribute("contentPage", "subthemes");
		}

		else {
			model.addAttribute("spheres", repoS.findAll());
			model.addAttribute("spheresId", spheresId);
			model.addAttribute("contentPage", "subspheres");
		}
		return "index";
	}
}
/*	@GetMapping("/spheres/{spheresId}")
	public String getSubspheres(Model model, @PathVariable int spheresId) {
		model.addAttribute("spheres", repoS.findAll());
		model.addAttribute("spheresId", spheresId);
		model.addAttribute("contentPage", "subspheres");
		return "index";
	}*/
	

