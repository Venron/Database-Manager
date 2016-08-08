package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class WertpapierDao extends AbstractJpaDao<Wertpapier> {

	public WertpapierDao(EntityManager em) {
		super(em);
		setClazz(Wertpapier.class);
	}
}
