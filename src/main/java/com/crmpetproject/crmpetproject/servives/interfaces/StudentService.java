package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.Student;

import java.util.List;

public interface StudentService extends CommonService<Student> {

    Student addStudentForClient(Client client);
    List<Student> getStudentsByStatusId(Long id);
    List<Student> getStudentsWithTodayNotificationsEnabled();
    void detach(Student student);
}
