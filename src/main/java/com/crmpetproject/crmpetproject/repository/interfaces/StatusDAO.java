package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.Status;
import com.crmpetproject.crmpetproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusDAO extends JpaRepository<Status, Long> {

	Status getStatusByName(String name);

	Status getStatusByClientsIn(List<Client> users);

    List<Status> getStatusesByClientsOwnerUser(User ownerUser);

	List<Status> getAllByOrderByIdAsc();

	@Query("SELECT MAX(s.position) from Status s")
	Long findMaxPosition();

	@Query("SELECT s FROM Status s WHERE s.createStudent = true")
	List<Status> getAllStatusesForStudents();
}
