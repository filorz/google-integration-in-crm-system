package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.Message;
import com.crmpetproject.crmpetproject.repository.interfaces.MessageRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends CommonServiceImpl<Message> implements MessageService {

	private final MessageRepository messageRepository;

	private static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	public MessageServiceImpl(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Override
	public Message addMessage(Message.Type type, String content) {
		logger.info("adding message...");
		return messageRepository.saveAndFlush(new Message(type, content));
	}
}
