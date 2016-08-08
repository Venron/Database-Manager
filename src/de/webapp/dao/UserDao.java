package de.webapp.dao;

import javax.persistence.EntityManager;

import de.webapp.model.*;

public class UserDao extends AbstractJpaDao<User> {

	public UserDao(EntityManager em) {
		super(em);
		setClazz(User.class);

	}
}
