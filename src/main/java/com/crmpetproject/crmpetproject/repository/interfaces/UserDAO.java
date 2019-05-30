package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Role;
import com.crmpetproject.crmpetproject.models.User;

import java.util.List;

public interface UserDAO extends CommonGenericRepository<User> {

	User getUserByEmailOrPhoneNumber(String email, String phoneNumber);

	User getUserByEmail(String email);

	List<User> getUserByRole(Role role);

	User getUserByFirstNameAndLastName(String firstName, String lastName);
}
