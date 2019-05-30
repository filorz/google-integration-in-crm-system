package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.ClientHistory;
import com.crmpetproject.crmpetproject.models.Student;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.ClientHistoryService;
import com.crmpetproject.crmpetproject.servives.interfaces.ClientService;
import com.crmpetproject.crmpetproject.servives.interfaces.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@RestController
@RequestMapping("/rest/student")
@PreAuthorize("hasAnyAuthority('OWNER')")
public class StudentRestController {

    private static Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    private final StudentService studentService;

    private final ClientService clientService;

    private final ClientHistoryService clientHistoryService;

    @Autowired
    public StudentRestController(StudentService studentService, ClientService clientService, ClientHistoryService clientHistoryService) {
        this.studentService = studentService;
        this.clientService = clientService;
        this.clientHistoryService = clientHistoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        ResponseEntity result;
        Student student = studentService.get(id);
        if (student != null) {
            result = ResponseEntity.ok(student);
        } else {
            logger.info("Student with id {} not found", id);
            result = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @PostMapping("/update")
    public HttpStatus updateStudent(@RequestBody Student student, @AuthenticationPrincipal User userFromSession) {
        Student previous = studentService.get(student.getId());
        Client client = previous.getClient();
        client.addHistory(clientHistoryService.createStudentUpdateHistory(userFromSession, previous, student, ClientHistory.Type.UPDATE_STUDENT));
        studentService.update(student);
        clientService.updateClient(client);
        return HttpStatus.OK;
    }

    @PostMapping("/delete/{id}")
    public HttpStatus deleteStudent(@PathVariable("id") Long id, @AuthenticationPrincipal User userFromSession) {
        Client client = studentService.get(id).getClient();
        client.addHistory(clientHistoryService.createHistory(userFromSession, client, ClientHistory.Type.DELETE_STUDENT));
        clientService.updateClient(client);
        studentService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<Client> getClientByStudentId(@PathVariable("id") Long id) {
        ResponseEntity result;
        Client client = studentService.get(id).getClient();
        if (client != null) {
            result = ResponseEntity.ok(client);
        } else {
            logger.info("Client for student with id {} not found", id);
            result = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return result;
    }
}
