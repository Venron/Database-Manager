package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class FondsanteilDao extends AbstractJpaDao<Fondsanteil> {

	public FondsanteilDao(EntityManager em) {
		super(em);
		setClazz(Fondsanteil.class);
	}
}
