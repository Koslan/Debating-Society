package ua.step.debating.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@GetMapping("/login")
	public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error);
	}
}
