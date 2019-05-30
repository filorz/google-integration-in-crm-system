package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Status;
import com.crmpetproject.crmpetproject.models.User;

import java.util.List;

public interface StatusService {

    List<Status> getAll();

    List<Status> getStatusesWithClientsForUser(User ownerUser);

    Status get(Long id);

    Status get(String name);

    Status getFirstStatusForClient();

	Status getStatusByName(String name);

	void add(Status status);

    void addInit(Status status);

    void update(Status status);

    void delete(Status status);

    void delete(Long id);

    Long findMaxPosition();

    List<Status> getAllStatusesForStudents();


}
