package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class RenteDao extends AbstractJpaDao<Rente> {

	public RenteDao(EntityManager em) {
		super(em);
		setClazz(Rente.class);
	}
}
