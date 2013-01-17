package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.TaskStatus;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "TaskStatus")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanTaskStatus implements BeanTaskStatusRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public TaskStatus persist (TaskStatus task) {
        em.persist (task);
        em.flush();
        em.refresh(task);
        return task;
    }

      public void delete (TaskStatus task) {
        TaskStatus t = em.merge (task);
        em.remove( t );
        em.flush();
      }

      public void update (TaskStatus task) {
        em.merge (task);
        em.flush();
      }

      public List <TaskStatus> findTaskStatus () {
    	  return (List <TaskStatus>) em.createQuery("select t from TaskStatus t").getResultList();
      }

      public TaskStatus findTaskStatus (int id) {
    	  return (TaskStatus) em.find(TaskStatus.class, id);
      }
      
	@Override
	public void logicalDelete(TaskStatus task) {
		TaskStatus t = em.merge (task);
        t.setTssIsdeleted((byte)1);
        update(t);
	}

}