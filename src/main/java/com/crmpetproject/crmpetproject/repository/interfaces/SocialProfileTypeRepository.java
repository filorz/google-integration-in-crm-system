package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.SocialProfileType;

public interface SocialProfileTypeRepository extends CommonGenericRepository<SocialProfileType> {
	SocialProfileType getSocialProfileTypeByName(String name);
}
