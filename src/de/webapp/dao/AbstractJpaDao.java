package de.webapp.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractJpaDao<T extends Serializable> {

	private Class<T> clazz;

	// @PersistenceContext
	EntityManager entityManager;

	public AbstractJpaDao(EntityManager em) {
		super();
		this.entityManager = em;
	}

	public final void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(int id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll() {
		return entityManager.createQuery("SELECT a from " + clazz.getSimpleName() + " a").getResultList();
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public T update(T entity) {
		return entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(int entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}
}
