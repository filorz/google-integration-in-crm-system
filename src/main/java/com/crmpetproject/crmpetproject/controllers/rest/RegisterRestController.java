package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class RegisterRestController {

    private static Logger logger = LoggerFactory.getLogger(RegisterRestController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/register")
    public ResponseEntity addUser(@Valid @RequestBody User user) {
        if ( user.isVerified() || user.isEnabled()) {
            logger.warn("CRM been attempt of hacking");
            return ResponseEntity.status(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS).build();
        }
        userService.add(user);
        logger.info("{} has register user: email {}", user.getFullName(), user.getEmail());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
