package com.crmpetproject.crmpetproject.controllers;

import com.crmpetproject.crmpetproject.servives.interfaces.ActiveClientStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student-status")
@PreAuthorize("hasAnyAuthority('OWNER')")
public class StudentStatusController {

    private static Logger logger = LoggerFactory.getLogger(StudentStatusController.class);

    private final ActiveClientStatusService studentStatusService;

    @Autowired
    public StudentStatusController(ActiveClientStatusService studentStatusService) {
        this.studentStatusService = studentStatusService;
    }

    @GetMapping(value = "/all")
    public ModelAndView showAllStudents() {
        ModelAndView modelAndView = new ModelAndView("all-students-status-table");
        modelAndView.addObject("statuses", studentStatusService.getAll());
        return modelAndView;
    }

}
