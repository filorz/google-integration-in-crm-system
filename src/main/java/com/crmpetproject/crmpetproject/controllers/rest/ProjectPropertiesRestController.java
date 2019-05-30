package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.ProjectProperties;
import com.crmpetproject.crmpetproject.servives.interfaces.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/properties")
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
public class ProjectPropertiesRestController {

    private final ProjectPropertiesService projectPropertiesService;

    @Autowired
    public ProjectPropertiesRestController(ProjectPropertiesService projectPropertiesService) {
        this.projectPropertiesService = projectPropertiesService;
    }

    @GetMapping
    public ResponseEntity<ProjectProperties> getProjectProperties() {
        return new ResponseEntity<>(projectPropertiesService.getOrCreate(), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Long> getStatus() {
        ProjectProperties projectProperties = projectPropertiesService.getOrCreate();
        Long status = -1L;
        if (projectProperties.getDefaultStatusId() != null) {
            status = projectProperties.getDefaultStatusId();
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
