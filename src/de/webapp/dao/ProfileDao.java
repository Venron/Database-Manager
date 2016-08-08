package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class ProfileDao extends AbstractJpaDao<Profile> {

	public ProfileDao(EntityManager em) {
		super(em);
		setClazz(Profile.class);

	}
}
