package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class BrancheDao extends AbstractJpaDao<Branche> {

	public BrancheDao(EntityManager em) {
		super(em);
		setClazz(Branche.class);
	}
}
