package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class FondsDao extends AbstractJpaDao<Fonds> {

	public FondsDao(EntityManager em) {
		super(em);
		setClazz(Fonds.class);
	}
}
