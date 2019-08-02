package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.ActiveClient;

import java.util.List;

public interface ActiveClientService extends CommonService<ActiveClient> {

    ActiveClient addActiveClientForClient(Client client);
    List<ActiveClient> getActiveClientByStatusId(Long id);
    List<ActiveClient> getStudentsWithTodayNotificationsEnabled();
    void detach(ActiveClient student);
}
