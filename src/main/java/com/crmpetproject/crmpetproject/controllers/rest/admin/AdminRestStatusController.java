package com.crmpetproject.crmpetproject.controllers.rest.admin;

import com.crmpetproject.crmpetproject.models.Status;
import com.crmpetproject.crmpetproject.models.User;
import com.crmpetproject.crmpetproject.servives.interfaces.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyAuthority('OWNER', 'ADMIN')")
@RequestMapping("/admin/rest/status")
public class AdminRestStatusController {

	private static Logger logger = LoggerFactory.getLogger(AdminRestStatusController.class);

	private final StatusService statusService;

	@Autowired
	public AdminRestStatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@PostMapping(value = "/edit")
	public ResponseEntity editStatus(@RequestParam(name = "statusName") String statusName,
                                     @RequestParam(name = "oldStatusId") Long oldStatusId,
                                     @RequestParam(name = "trialOffset") Integer trialOffset,
                                     @RequestParam(name = "nextPaymentOffset") Integer nextPaymentOffset,
                                     @AuthenticationPrincipal User currentAdmin) {
		Status status = statusService.get(oldStatusId);
		status.setName(statusName);
		status.setTrialOffset(trialOffset);
		status.setNextPaymentOffset(nextPaymentOffset);
		statusService.update(status);
		logger.info("{} has updated status {}", currentAdmin.getFullName(), statusName);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/delete")
	public ResponseEntity deleteStatus(@RequestParam(name = "deleteId") Long deleteId,
                                       @AuthenticationPrincipal User currentAdmin) {
		statusService.delete(deleteId);
		logger.info("{} has  deleted status  with id {}", currentAdmin.getFullName(), deleteId);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/visible/change")
	public ResponseEntity changeVisibleStatus(@RequestParam("statusId") long statusId,
                                              @RequestParam("invisible") boolean bool) {
		Status status = statusService.get(statusId);
		if (status.getInvisible() == bool) {
			String reason = "Статус уже " + (bool ? "невидимый" : "видимый");
			logger.error(reason);
			return ResponseEntity.badRequest().body(reason);
		}
		status.setInvisible(bool);
		statusService.update(status);
		return ResponseEntity.ok().body(status);
	}

}
