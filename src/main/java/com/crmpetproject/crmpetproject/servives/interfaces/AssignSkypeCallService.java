package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.AssignSkypeCall;
import java.util.List;

public interface AssignSkypeCallService extends CommonService<AssignSkypeCall> {

	void addSkypeCall(AssignSkypeCall assignSkypeCall);

	List<AssignSkypeCall> getSkypeCallDate();
}