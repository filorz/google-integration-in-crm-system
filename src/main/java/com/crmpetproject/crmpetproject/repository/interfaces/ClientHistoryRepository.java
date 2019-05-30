package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.ClientHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientHistoryRepository extends JpaRepository<ClientHistory, Long> {

	List<ClientHistory> getByClientId(long id);

	List<ClientHistory> getAllByClientId(long id, Pageable pageable);
}
