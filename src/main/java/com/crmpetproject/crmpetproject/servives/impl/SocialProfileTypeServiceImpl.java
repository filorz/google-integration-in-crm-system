package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.SocialProfileType;
import com.crmpetproject.crmpetproject.repository.interfaces.SocialProfileTypeRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.SocialProfileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialProfileTypeServiceImpl extends CommonServiceImpl<SocialProfileType> implements SocialProfileTypeService {
    private SocialProfileTypeRepository socialProfileTypeRepository;

    @Autowired
    public SocialProfileTypeServiceImpl(SocialProfileTypeRepository socialProfileTypeRepository) {
        this.socialProfileTypeRepository = socialProfileTypeRepository;
    }

    @Override
    public SocialProfileType getByTypeName(String name) {
        return socialProfileTypeRepository.getSocialProfileTypeByName(name);
    }

}
