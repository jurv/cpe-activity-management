package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Task;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "Task")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanTask implements BeanTaskRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public Task persist (Task task) {
        em.persist (task);
        em.flush();
        em.refresh(task);
        return task;
    }

      public void delete (Task task) {
        Task t = em.merge (task);
        em.remove( t );
        em.flush();
      }

      public void update (Task task) {
        em.merge (task);
        em.flush();
      }

      public List <Task> findTasks () {
    	  return (List <Task>) em.createQuery("select t from Task t").getResultList();
      }

      public List <Task> findActiveTasks () {
    	  return (List <Task>) em.createQuery("select t from Task t where tsk_isdeleted = 'O'").getResultList();
      }

      public Task findTask (int id) {
    	  return (Task) em.find(Task.class, id);
      }
      
      public List <Task> findTasksByUser (int userId) {
    	  return (List <Task>) em.createQuery("select t from Task t where usr_assignedto_id = '" + userId + "'").getResultList();
      }

	@Override
	public void logicalDelete(Task task) {
		Task t = em.merge (task);
        t.setTskIsdeleted((byte)1);
        update(t);
	}

}