package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.ActiveClientStatus;
import com.crmpetproject.crmpetproject.servives.interfaces.ActiveClientService;
import com.crmpetproject.crmpetproject.servives.interfaces.ActiveClientStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/student/status")
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
public class StudentStatusRestController {

    private static Logger logger = LoggerFactory.getLogger(StudentStatusRestController.class);

    private final ActiveClientStatusService studentStatusService;
    private final ActiveClientService studentService;

    @Autowired
    public StudentStatusRestController(ActiveClientStatusService studentStatusService, ActiveClientService studentService) {
        this.studentStatusService = studentStatusService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<ActiveClientStatus>> getAllStudentStatuses() {
        return ResponseEntity.ok(studentStatusService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActiveClientStatus> getStudentById(@PathVariable("id") Long id) {
        ResponseEntity result;
        ActiveClientStatus status = studentStatusService.get(id);
        if (status != null) {
            result = ResponseEntity.ok(status);
        } else {
            logger.info("Student status with id {} not found", id);
            result = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    public HttpStatus deleteStudentStatus(@PathVariable("id") Long id) {
        HttpStatus result;
        if (studentService.getActiveClientByStatusId(id).isEmpty()) {
            studentStatusService.delete(id);
            result = HttpStatus.OK;
        } else {
            logger.info("StudentStatus with id {} can not be deleted, because its used by Students", id);
            result = HttpStatus.CONFLICT;
        }
        return result;
    }

    @PostMapping("/create")
    public HttpStatus createStudentStatus(@RequestBody ActiveClientStatus studentStatus) {
        studentStatusService.add(studentStatus);
        return HttpStatus.OK;
    }

    @PostMapping("/update")
    public HttpStatus updateStudentStatus(@RequestBody ActiveClientStatus status) {
        studentStatusService.update(status);
        return HttpStatus.OK;
    }

}
