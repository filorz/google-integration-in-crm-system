package com.crmpetproject.crmpetproject.exceptions.user;

public class UserPhotoException extends RuntimeException {
	public UserPhotoException() {
			super("Произошла ошибка сохранения фотографии");
		}
}
