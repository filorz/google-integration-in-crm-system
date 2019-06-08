package com.crmpetproject.crmpetproject.component;

import com.crmpetproject.crmpetproject.models.AssignSkypeCall;
import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@EnableScheduling
//@PropertySource(value = "file:./skype-message.properties", encoding = "Cp1251")
public class ScheduleTasks {

	private final ClientService clientService;

	private final AssignSkypeCallService assignSkypeCallService;

	private Environment env;

	private static Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);

	@Autowired
	public ScheduleTasks(ClientService clientService,
						 AssignSkypeCallService assignSkypeCallService,
						 Environment env) {
		this.clientService = clientService;
		this.assignSkypeCallService = assignSkypeCallService;
		this.env = env;
	}

//	@Scheduled(fixedRate = 6_000)
//	private void checkTimeSkypeCall() {
//		for (AssignSkypeCall assignSkypeCall : assignSkypeCallService.getSkypeCallDate()) {
//			Client client = assignSkypeCall.getToAssignSkypeCall();
//			String skypeTemplate = env.getRequiredProperty("skype.template");
//			User principal = assignSkypeCall.getFromAssignSkypeCall();
//			String selectNetworks = assignSkypeCall.getSelectNetworkForNotifications();
//			Long clientId = client.getId();
//			String dateOfSkypeCall = LocalDateTime.parse(assignSkypeCall.getRemindBeforeOfSkypeCall().toString())
//					.plusHours(1).format(DateTimeFormatter.ofPattern("dd MMMM в HH:mm по МСК"));
//
//			if (selectNetworks.contains("vk")) {
//				try {
//					vkService.sendMessageToClient(clientId, skypeTemplate, dateOfSkypeCall, principal);
//				} catch (Exception e) {
//					logger.warn("VK message not sent", e);
//				}
//			}
//			assignSkypeCallService.delete(assignSkypeCall);
//			clientService.updateClient(client);
//		}
//	}

	@Scheduled(fixedRate = 6_000)
	private void checkClientActivationDate() {
		for (Client client : clientService.getChangeActiveClients()) {
			client.setPostponeDate(null);
			clientService.updateClient(client);
		}
	}

}