package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientHistoryService {

	ClientHistory addHistory(ClientHistory history);

	ClientHistory createHistory(String socialRequest);

	ClientHistory createHistory(User user, Client client, ClientHistory.Type type);

	ClientHistory createHistory(User admin, User worker, Client client, ClientHistory.Type type);

	ClientHistory createHistory(User user, Client client, ClientHistory.Type type, String link);

	ClientHistory createInfoHistory(User user, Client client, ClientHistory.Type type, String info);

	ClientHistory createHistory(User user, String recordLink);

	ClientHistory createHistory(User user, Client client, Message message);

	ClientHistory createHistory(User admin, Client prev, Client current, ClientHistory.Type type);

	ClientHistory creteStudentHistory(User user, ClientHistory.Type type);

	ClientHistory createStudentUpdateHistory(User user, ActiveClient prev, ActiveClient current, ClientHistory.Type type);

	List<ClientHistory> getClientById(long id);

	List<ClientHistory> getAllClientById(long id, Pageable pageable);
}
