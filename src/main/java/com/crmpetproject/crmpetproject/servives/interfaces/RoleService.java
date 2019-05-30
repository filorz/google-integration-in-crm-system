package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.Role;

public interface RoleService extends CommonService<Role> {

	Role getRoleByName(String roleName);
}
