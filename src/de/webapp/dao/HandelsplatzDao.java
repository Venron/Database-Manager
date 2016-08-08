package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class HandelsplatzDao extends AbstractJpaDao<Handelsplatz> {

	public HandelsplatzDao(EntityManager em) {
		super(em);
		setClazz(Handelsplatz.class);
	}
}
