package com.crmpetproject.crmpetproject.exceptions.member;

public class NotFoundMemberList extends RuntimeException {
    public NotFoundMemberList(){
        super("Лист подписчиков сообщества не был получен");
    }
}
