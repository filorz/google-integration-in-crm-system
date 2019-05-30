package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Student;

import java.util.List;

public interface StudentRepository extends CommonGenericRepository<Student> {

    List<Student> getStudentsByStatusId(Long id);
}
