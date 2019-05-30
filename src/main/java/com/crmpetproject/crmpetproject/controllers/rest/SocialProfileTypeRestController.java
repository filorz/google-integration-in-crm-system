package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.SocialProfileType;
import com.crmpetproject.crmpetproject.servives.interfaces.SocialProfileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
public class SocialProfileTypeRestController {

	private final SocialProfileTypeService socialProfileTypeService;

	@Autowired
	public SocialProfileTypeRestController(SocialProfileTypeService socialProfileTypeService) {
		this.socialProfileTypeService = socialProfileTypeService;
	}

	@GetMapping(value = "/all-socialProfileTypes")
	public ResponseEntity getAllSocialProfileTypes() {
		List<SocialProfileType> socialProfileTypes = socialProfileTypeService.getAll();
		return ResponseEntity.ok(socialProfileTypes);
	}
}
