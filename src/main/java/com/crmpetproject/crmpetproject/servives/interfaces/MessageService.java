package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Message;

public interface MessageService extends CommonService<Message> {
    Message addMessage(Message.Type type, String content);
}
