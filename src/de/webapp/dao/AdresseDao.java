package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class AdresseDao extends AbstractJpaDao<Adresse> {

	public AdresseDao(EntityManager em) {
		super(em);
		setClazz(Adresse.class);
	}
}
