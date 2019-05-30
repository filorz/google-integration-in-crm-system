package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Student;

import java.util.List;

public interface StudentRepositoryCustom {

    List<Student> getStudentsWithTodayNotificationsEnabled();

    void detach(Student student);
}
