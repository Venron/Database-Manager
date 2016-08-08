package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class NachrichtDao extends AbstractJpaDao<Nachricht> {

	public NachrichtDao(EntityManager em) {
		super(em);
		setClazz(Nachricht.class);

	}
}
