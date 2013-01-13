package com.cpeeterprise;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import model.User2Project;

/**
 * Session Bean implementation class BeanUser
 */
@Stateful(mappedName = "User2Project")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanUser2Project implements BeanUser2ProjectRemote{

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	public BeanUser2Project() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public User2Project persist(User2Project user2project) {
		em.persist(user2project);
		em.flush();
		em.refresh(user2project);
        return user2project;
	}

	@Override
	public void delete(User2Project user2project) {
		User2Project t = em.merge(user2project);
		em.remove(t);
	}

	@Override
	public void update(User2Project user2project) {
		em.merge(user2project);
		em.flush();
	}
}
