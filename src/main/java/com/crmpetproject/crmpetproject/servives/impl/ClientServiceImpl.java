package com.crmpetproject.crmpetproject.servives.impl;


import com.crmpetproject.crmpetproject.exceptions.client.ClientExistsException;
import com.crmpetproject.crmpetproject.models.*;
import com.crmpetproject.crmpetproject.repository.interfaces.ClientRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.ClientService;
import com.crmpetproject.crmpetproject.servives.interfaces.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientServiceImpl extends CommonServiceImpl<Client> implements ClientService {

	private final ClientRepository clientRepository;

	private StatusService statusService;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<Client> getAllClientsByStatus(Status status) {
		return clientRepository.getAllByStatus(status);
	}

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientBySkype(String skypeLogin) {
		return clientRepository.getClientBySkype(skypeLogin);
	}

	@Override
	public List<Client> getClientsByOwnerUser(User ownerUser) {
		return clientRepository.getClientsByOwnerUser(ownerUser);
	}

	@Override
	public Client getClientByEmail(String email) {
		return clientRepository.getClientByEmail(email);
	}

	@Override
	public Client getClientByPhoneNumber(String phoneNumber) {
		return clientRepository.getClientByPhoneNumber(phoneNumber);
	}

	@Override
	public Client getClientBySocialProfile(SocialProfile socialProfile) {
		List<SocialProfile> socialProfiles = new ArrayList<>();
		socialProfiles.add(socialProfile);
		return clientRepository.getClientBySocialProfiles(socialProfiles);
	}

	@Override
	public Client getClientByID(Long id) {
		Optional<Client> optional = clientRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Client> filteringClient(FilteringCondition filteringCondition) {
		return clientRepository.filteringClient(filteringCondition);
	}

	@Override
	public List<Client> getChangeActiveClients() {
		return clientRepository.getChangeActiveClients();
	}

	@Override
	public List<Client> getClientsByManyIds(List<Long> ids) {
		return clientRepository.getById(ids);
	}

	@Override
	public void updateBatchClients(List<Client> clients) {
		clientRepository.updateBatchClients(clients);
	}

	@Override
	public void addBatchClients(List<Client> clients) {
		clientRepository.addBatchClients(clients);
	}

	@Override
	public void addClient(Client client) {
		if (client.getLastName() == null) {
			client.setLastName("");
		}
		checkSocialLinks(client);
		Status firstStatus = statusService.getFirstStatusForClient();
		if (client.getPhoneNumber() != null && !client.getPhoneNumber().isEmpty()) {
			phoneNumberValidation(client);
			Client clientByPhone = clientRepository.getClientByPhoneNumber(client.getPhoneNumber());
			if (clientByPhone != null) {
				clientByPhone.setStatus(firstStatus);
				clientRepository.saveAndFlush(clientByPhone);
				return;
			}
		}
		if (client.getEmail() != null && !client.getEmail().isEmpty()) {
			Client clientByEmail = clientRepository.getClientByEmail(client.getEmail());
			if (clientByEmail != null) {
				clientByEmail.setStatus(firstStatus);
				clientRepository.saveAndFlush(clientByEmail);
				return;
			}
		}
		clientRepository.saveAndFlush(client);
	}

	@Override
	public List<String> getClientsEmails() {
		return clientRepository.getClientsEmail();
	}

	@Override
	public List<String> getClientsPhoneNumbers() {
		return clientRepository.getClientsPhoneNumber();
	}

	@Override
	public List<String> getFilteredClientsEmail(FilteringCondition filteringCondition) {
		return clientRepository.getFilteredClientsEmail(filteringCondition);
	}

	@Override
	public List<String> getFilteredClientsPhoneNumber(FilteringCondition filteringCondition) {
		return clientRepository.getFilteredClientsPhoneNumber(filteringCondition);
	}

	@Override
	public List<String> getFilteredClientsSNLinks(FilteringCondition filteringCondition) {
		return clientRepository.getFilteredClientsSNLinks(filteringCondition);
	}

	@Override
	public List<Client> getClientsByStatusAndOwnerUserOrOwnerUserIsNull(Status status, User ownUser) {
		return clientRepository.getByStatusAndOwnerUserOrOwnerUserIsNull(status, ownUser);
	}

	@Override
	public List<Client> getAllClientsByPage(Pageable pageable) {
		return clientRepository.findAll(pageable).getContent();
	}

	@Override
	public void updateClient(Client client) {
		if (client.getEmail() != null && !client.getEmail().isEmpty()) {
			Client clientByMail = clientRepository.getClientByEmail(client.getEmail());
			if (clientByMail != null && !clientByMail.getId().equals(client.getId())) {
				throw new ClientExistsException();
			}
		}
		if (client.getPhoneNumber() != null && !client.getPhoneNumber().isEmpty()) {
			phoneNumberValidation(client);
			Client clientByPhone = clientRepository.getClientByPhoneNumber(client.getPhoneNumber());
			if (clientByPhone != null && !clientByPhone.getId().equals(client.getId())) {
				throw new ClientExistsException();
			}
		}
		checkSocialLinks(client);
		clientRepository.saveAndFlush(client);
	}

	private void phoneNumberValidation(Client client) {
		String phoneNumber = client.getPhoneNumber();
		Pattern pattern = Pattern.compile("^((8|\\+7|7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
		Matcher matcher = pattern.matcher(phoneNumber);
		if (matcher.matches()) {
			client.setCanCall(true);
			if (phoneNumber.startsWith("8")) {
				phoneNumber = phoneNumber.replaceFirst("8", "7");
			}
			client.setPhoneNumber(phoneNumber.replaceAll("[+()-]", "")
					.replaceAll("\\s", ""));
		} else {
			client.setCanCall(false);
		}
	}

	private void checkSocialLinks(Client client) {
		for (int i = 0; i < client.getSocialProfiles().size(); i++) {
			String link = client.getSocialProfiles().get(i).getLink();
			SocialProfileType type = client.getSocialProfiles().get(i).getSocialProfileType();
			if (type.getName().equals("unknown")) {
				if (!link.startsWith("https")) {
					if (link.startsWith("http")) {
						link = link.replaceFirst("http", "https");
					} else {
						link = "https://" + link;
					}
				}
			} else {
				int indexOfLastSlash = link.lastIndexOf("/");
				if (indexOfLastSlash != -1) {
					link = link.substring(indexOfLastSlash + 1);
				}
				link = "https://" + type.getName() + ".com/" + link;
			}
			client.getSocialProfiles().get(i).setLink(link);
		}
	}

	@Override
	public List<Client> getClientsBySearchPhrase(String search) {
		return clientRepository.getClientsBySearchPhrase(search);
	}

	@Autowired
	private void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
}
