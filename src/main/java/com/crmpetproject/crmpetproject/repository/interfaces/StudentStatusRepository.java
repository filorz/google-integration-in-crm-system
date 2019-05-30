package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.StudentStatus;

public interface StudentStatusRepository extends CommonGenericRepository<StudentStatus> {

    StudentStatus getStudentStatusByStatus(String name);
}
