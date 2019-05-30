package com.crmpetproject.crmpetproject.configuration.inteface;

public interface MailConfig {
    String getLogin();
    String getPassword();
    String getMailFrom();
    String getSocketFactoryClass();
    String getSocketFactoryFallback();
    String getProtocol();
    String getDebug();
    String getImapServer();
}
