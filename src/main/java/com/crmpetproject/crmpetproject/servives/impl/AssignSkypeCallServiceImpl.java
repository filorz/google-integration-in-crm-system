package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.AssignSkypeCall;
import com.crmpetproject.crmpetproject.repository.interfaces.AssignSkypeCallRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.AssignSkypeCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignSkypeCallServiceImpl extends CommonServiceImpl<AssignSkypeCall> implements AssignSkypeCallService {

	private AssignSkypeCallRepository assignSkypeCallRepository;

	@Autowired
	public AssignSkypeCallServiceImpl(AssignSkypeCallRepository assignSkypeCallRepository) {
		this.assignSkypeCallRepository = assignSkypeCallRepository;
	}

	@Override
	public void addSkypeCall(AssignSkypeCall assignSkypeCall) {
		assignSkypeCallRepository.saveAndFlush(assignSkypeCall);
	}

	@Override
	public List<AssignSkypeCall> getSkypeCallDate() {
		return assignSkypeCallRepository.getSkypeCallDate();
	}
}