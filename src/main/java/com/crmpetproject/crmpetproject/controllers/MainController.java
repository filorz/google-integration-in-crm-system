package com.crmpetproject.crmpetproject.controllers;

import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private final UserService userService;

	public MainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = {"/", "/login"})
	public String homePage() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			return "login";
		} else {
			return "redirect:/client";
		}
	}

	@GetMapping("/about-author")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ModelAndView aboutMePage(@AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("about-me");
		modelAndView.addObject("user", userService.get(userFromSession.getId()));
		return modelAndView;
	}
}
