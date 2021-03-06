package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.SocialProfileType;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.SocialProfileTypeService;
import com.crmpetproject.crmpetproject.servives.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestController {

    private final UserService userService;
    private final SocialProfileTypeService socialProfileTypeService;

    @Autowired
    public UserRestController(UserService userService,
							  SocialProfileTypeService socialProfileTypeService) {
        this.userService = userService;
        this.socialProfileTypeService = socialProfileTypeService;
    }

	@GetMapping(value = "/rest/user", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ResponseEntity<List<User>> getAll(@AuthenticationPrincipal User userFromSession) {
		List <User> users = userService.getAll();
		users.remove(userService.get(userFromSession.getId()));
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = {"/user/socialNetworkTypes"})
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ResponseEntity<Map<Long, String>> getSocialNetworkTypes() {
		List<SocialProfileType> socialProfileTypes = socialProfileTypeService.getAll();
		Map<Long, String> socialTypeNames = new HashMap<>();
		for (SocialProfileType socialProfileType : socialProfileTypes) {
			socialTypeNames.put(socialProfileType.getId(), socialProfileType.getName());
		}
		return ResponseEntity.ok(socialTypeNames);
	}

	@GetMapping("rest/client/getPrincipal")
	public ResponseEntity getPrincipal(@AuthenticationPrincipal User userFromSession) {
		return ResponseEntity.ok(userFromSession);
	}
}
