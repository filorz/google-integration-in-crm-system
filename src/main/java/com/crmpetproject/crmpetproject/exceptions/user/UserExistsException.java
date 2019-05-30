package com.crmpetproject.crmpetproject.exceptions.user;

public class UserExistsException extends RuntimeException {
	public UserExistsException() {
		super("Пользователь c таким e-mail уже существует");
	}
}

