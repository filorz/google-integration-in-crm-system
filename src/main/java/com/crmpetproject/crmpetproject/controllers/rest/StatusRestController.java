package com.crmpetproject.crmpetproject.controllers.rest;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.ClientHistory;
import com.crmpetproject.crmpetproject.models.Status;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/status")
public class StatusRestController {

	private static Logger logger = LoggerFactory.getLogger(StatusRestController.class);

	private final StatusService statusService;
	private final ClientService clientService;
	private final ClientHistoryService clientHistoryService;
	private final ActiveClientService studentService;

	@Autowired
	public StatusRestController(StatusService statusService,
								ClientService clientService,
								ClientHistoryService clientHistoryService,
								ActiveClientService studentService) {
		this.statusService = statusService;
		this.clientService = clientService;
		this.clientHistoryService = clientHistoryService;
		this.studentService = studentService;
	}

	@GetMapping(value = "/all-statuses")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ResponseEntity getAllStstuses() {
		Client.State[] statuses = Client.State.values();
		return ResponseEntity.ok(statuses);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public ResponseEntity<List<Client>> getStatusByID(@PathVariable Long id) {
		Status status = statusService.get(id);
		return ResponseEntity.ok(clientService.getAllClientsByStatus(status));
	}

	@PostMapping(value = "/add")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
	public ResponseEntity addNewStatus(@RequestParam(name = "statusName") String statusName,
                                       @AuthenticationPrincipal User currentAdmin) {
		statusService.add(new Status(statusName));
		logger.info("{} has added status with name: {}", currentAdmin.getFullName(), statusName);
		return ResponseEntity.ok("Успешно добавлено");
	}

	@PostMapping(value = "/client/change")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ResponseEntity changeClientStatus(@RequestParam(name = "statusId") Long statusId,
                                             @RequestParam(name = "clientId") Long clientId,
                                             @AuthenticationPrincipal User userFromSession) {
		Client currentClient = clientService.get(clientId);
		if (currentClient.getStatus().getId().equals(statusId)) {
			return ResponseEntity.badRequest().body("Клиент уже находится на данном статусе");
		}
		currentClient.setStatus(statusService.get(statusId));
		currentClient.addHistory(clientHistoryService.createHistory(userFromSession, currentClient, ClientHistory.Type.STATUS));
		if (currentClient.getActiveClient() == null) {
			currentClient.addHistory(clientHistoryService.creteStudentHistory(userFromSession, ClientHistory.Type.ADD_STUDENT));
		}
		clientService.updateClient(currentClient);
		studentService.addActiveClientForClient(currentClient);
		logger.info("{} has changed status of client with id: {} to status id: {}", userFromSession.getFullName(), clientId, statusId);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/client/delete")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public ResponseEntity deleteClientStatus(@RequestParam("clientId") long clientId,
                                             @AuthenticationPrincipal User userFromSession) {
		Client client = clientService.get(clientId);
		if (client == null) {
			logger.error("Can`t delete client status, client with id = {} not found", clientId);
			return ResponseEntity.notFound().build();
		}
		Status status = client.getStatus();
		client.setStatus(statusService.get("deleted"));
		client.addHistory(clientHistoryService.createHistory(userFromSession, client, ClientHistory.Type.STATUS));
		clientService.updateClient(client);
		logger.info("{} delete client with id = {} in status {}", userFromSession.getFullName(), client.getId(), status.getName());
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/position/change")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity changePositionOfTwoStatuses(@RequestParam("sourceId") long sourceId,
                                                      @RequestParam("destinationId") long destinationId) {
		Status sourceStatus = statusService.get(sourceId);
		Status destinationStatus = statusService.get(destinationId);
		if (sourceStatus == null || destinationStatus == null) {
			return ResponseEntity.notFound().build();
		}
		Long tempPosition = sourceStatus.getPosition();
		sourceStatus.setPosition(destinationStatus.getPosition());
		destinationStatus.setPosition(tempPosition);
		statusService.update(sourceStatus);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/create-student")
	@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN', 'USER')")
	public HttpStatus setCreateStudent(@RequestParam("id") Long id, @RequestParam("create") Boolean create) {
		Status status = statusService.get(id);
		status.setCreateStudent(create);
		statusService.update(status);
		return HttpStatus.OK;
	}
}
