package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.SocialProfile;
import com.crmpetproject.crmpetproject.repository.interfaces.SocialProfileRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.SocialProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialProfileServiceImpl implements SocialProfileService {

	private final SocialProfileRepository socialProfileRepository;

	@Autowired
	public SocialProfileServiceImpl(SocialProfileRepository socialProfileRepository) {
		this.socialProfileRepository = socialProfileRepository;
	}

	@Override
	public SocialProfile getSocialProfileByLink(String link) {
		return socialProfileRepository.getByLink(link);
	}
}
