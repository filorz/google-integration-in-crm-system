package com.crmpetproject.crmpetproject.repository.interfaces;

import com.crmpetproject.crmpetproject.models.SocialProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {

	SocialProfile getByLink(String ling);
}
