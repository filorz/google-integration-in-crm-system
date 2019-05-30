package com.crmpetproject.crmpetproject.exceptions.client;

public class ClientExistsException extends RuntimeException {
	public ClientExistsException() {
		super("Пользователь уже существует");
	}
}
