package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class UnternehmensdatenDao extends AbstractJpaDao<Unternehmensdaten> {

	public UnternehmensdatenDao(EntityManager em) {
		super(em);
		setClazz(Unternehmensdaten.class);
	}
}
