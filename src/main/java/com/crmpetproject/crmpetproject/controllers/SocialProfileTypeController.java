package com.crmpetproject.crmpetproject.controllers;

import com.crmpetproject.crmpetproject.models.SocialProfileType;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.SocialProfileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
public class SocialProfileTypeController {

	private final SocialProfileTypeService socialProfileTypeService;

	@Autowired
	public SocialProfileTypeController(SocialProfileTypeService socialProfileTypeService) {
		this.socialProfileTypeService = socialProfileTypeService;
	}

	@GetMapping(value = "/admin/user/socialProfileTypes")
	public ModelAndView socialProfileTypes(ModelAndView modelAndView,
										   @AuthenticationPrincipal User userFromSession) {
		List<SocialProfileType> socialProfileTypes = socialProfileTypeService.getAll();
		modelAndView.addObject("socialProfileTypes", socialProfileTypes);
		modelAndView.setViewName("socialProfileTypes-table");
		return modelAndView;
	}

	@PostMapping(value = "/admin/user/addSocialProfileType")
	public ModelAndView addSocialProfileType(@ModelAttribute SocialProfileType socialProfileType) {
		socialProfileTypeService.add(socialProfileType);
		return new ModelAndView("redirect:/admin/user/socialProfileTypes");
	}
}
