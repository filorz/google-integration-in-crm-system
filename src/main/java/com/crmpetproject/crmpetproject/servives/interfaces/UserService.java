package com.crmpetproject.crmpetproject.servives.interfaces;


import com.crmpetproject.crmpetproject.models.Role;
import com.crmpetproject.crmpetproject.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends CommonService<User> {

	User getByEmailOrPhone(String email, String phone);

	User add(User user);

	void update(User user);

	void addPhoto(MultipartFile file, User user);

	List<User> getByRole(Role role);

	User getUserByEmail(String email);

	User getUserByFirstNameAndLastName(String firstName, String lastName);
}
