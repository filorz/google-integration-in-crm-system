package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.ActiveClientStatus;

public interface ActiveClientStatusRepository extends CommonGenericRepository<ActiveClientStatus> {

    ActiveClientStatus getStudentStatusByStatus(String name);
}
