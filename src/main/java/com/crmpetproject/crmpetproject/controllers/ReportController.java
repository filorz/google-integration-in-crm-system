package com.crmpetproject.crmpetproject.controllers;

import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
public class ReportController {

	@Autowired
	private StatusService statusService;

	@GetMapping(value = "/report")
	public ModelAndView trackingGroupInfo(@AuthenticationPrincipal User userFromSession) {
		ModelAndView modelAndView = new ModelAndView("report-clients");
		modelAndView.addObject("statuses", statusService.getAll());
		modelAndView.addObject("currentMail", userFromSession.getEmail());
		return modelAndView;
	}
}
