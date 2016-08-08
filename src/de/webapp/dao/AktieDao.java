package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class AktieDao extends AbstractJpaDao<Aktie> {

	public AktieDao(EntityManager em) {
		super(em);
		setClazz(Aktie.class);
	}
}
