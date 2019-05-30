package com.crmpetproject.crmpetproject.controllers;

import com.crmpetproject.crmpetproject.configuration.ImageConfig;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.RoleService;
import com.crmpetproject.crmpetproject.servives.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;
	private final RoleService roleService;
	private final ImageConfig imageConfig;

	@Autowired
	public UserController(UserService userService,
						  RoleService roleService,
						  ImageConfig imageConfig) {
		this.userService = userService;
		this.roleService = roleService;
		this.imageConfig = imageConfig;
	}

	@GetMapping(value = "/admin/user/{id}")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
	public ModelAndView clientInfo(@PathVariable Long id,
								   @AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("user-settings-profile");
		modelAndView.addObject("user", userService.get(id));
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("maxSize", imageConfig.getMaxImageSize());
		return modelAndView;
	}

	@GetMapping(value = "/admin/user/add")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
	public ModelAndView addUser(@AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("add-user");
		modelAndView.addObject("roles", roleService.getAll());
		modelAndView.addObject("maxSize", imageConfig.getMaxImageSize());
		return modelAndView;
	}

	@GetMapping(value = "/user/register")
	public ModelAndView registerUser() {
		ModelAndView modelAndView = new ModelAndView("user-registration");
		modelAndView.addObject("maxSize", imageConfig.getMaxImageSize());
		return modelAndView;
	}

	@GetMapping(value = "/user/settings")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ModelAndView getUserCustomize(@AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("settings");
		modelAndView.addObject("user", userService.get(userFromSession.getId()));
		modelAndView.addObject("userCustomize", userService.get(userFromSession.getId()));
		return modelAndView;
	}

	@PostMapping(value = "/user/autoAnswer")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ModelAndView changeAutoAnswer(@RequestParam String text,
											@AuthenticationPrincipal User userFromSession) {
	    userFromSession.setAutoAnswer(text);
		userService.update(userFromSession);
		return new ModelAndView("redirect:/user/settings");
	}
	@GetMapping(value = "/user/autoAnswer")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ModelAndView getAutoAnswerView(@AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("user-autoanswer");
		modelAndView.addObject("userCustomize",userFromSession);
		return modelAndView;
	}

}
