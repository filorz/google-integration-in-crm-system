package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.ActiveClient;

import java.util.List;

public interface ActiveClientRepository extends CommonGenericRepository<ActiveClient> {

    List<ActiveClient> getActiveClientByStatusId(Long id);
}
