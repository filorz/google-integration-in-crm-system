package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.Role;
import com.crmpetproject.crmpetproject.repository.interfaces.RoleDAO;
import com.crmpetproject.crmpetproject.servives.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CommonServiceImpl<Role> implements RoleService {
	private final RoleDAO roleDAO;

	@Autowired
	public RoleServiceImpl(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public Role getRoleByName(String roleName) {
		return roleDAO.getByRoleName(roleName);
	}
}
