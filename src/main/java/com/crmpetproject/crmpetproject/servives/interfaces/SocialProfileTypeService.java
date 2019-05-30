package com.crmpetproject.crmpetproject.servives.interfaces;

import com.crmpetproject.crmpetproject.models.SocialProfileType;

public interface SocialProfileTypeService extends CommonService<SocialProfileType>{

    SocialProfileType getByTypeName(String name);
}
