package com.crmpetproject.crmpetproject.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "social_network_type")
public class SocialProfileType implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "social_network_social_network_type",
			inverseJoinColumns = {@JoinColumn(name = "social_network_id", foreignKey = @ForeignKey(name = "FK_SOCIAL_NETWORK_SOCIAL_NETWORK_TYPE"))},
			joinColumns = {@JoinColumn(name = "social_network_type_id", foreignKey = @ForeignKey(name = "FK_SOCIAL_NETWORK"))})
	private List<SocialProfile> socialProfileList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SocialProfileType() {
	}

	public SocialProfileType(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SocialProfileType)) return false;
		SocialProfileType that = (SocialProfileType) o;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public List<SocialProfile> getSocialProfileList() {
		return socialProfileList;
	}

	public void setSocialProfileList(List<SocialProfile> socialProfileList) {
		this.socialProfileList = socialProfileList;
	}
}
