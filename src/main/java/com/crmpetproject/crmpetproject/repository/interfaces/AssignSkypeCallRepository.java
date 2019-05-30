package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.AssignSkypeCall;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignSkypeCallRepository extends CommonGenericRepository<AssignSkypeCall> {

	@Query(value = "select sl from AssignSkypeCall sl where now() >= sl.remindBeforeOfSkypeCall")
	List<AssignSkypeCall> getSkypeCallDate();
}