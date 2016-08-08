package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class KursDao extends AbstractJpaDao<Kurs> {

	public KursDao(EntityManager em) {
		super(em);
		setClazz(Kurs.class);
	}
}
