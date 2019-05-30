package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.Role;

public interface RoleDAO extends CommonGenericRepository<Role> {
	Role getByRoleName(String roleName);
}
