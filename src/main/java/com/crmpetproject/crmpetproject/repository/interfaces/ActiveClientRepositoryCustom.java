package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.ActiveClient;

import java.util.List;

public interface ActiveClientRepositoryCustom {

    List<ActiveClient> getStudentsWithTodayNotificationsEnabled();

    void detach(ActiveClient student);
}
