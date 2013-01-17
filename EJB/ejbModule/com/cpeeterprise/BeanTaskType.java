package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Task;
import model.TaskType;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "TaskType")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanTaskType implements BeanTaskTypeRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public TaskType persist (TaskType taskType) {
        em.persist (taskType);
        em.flush();
        em.refresh(taskType);
        return taskType;
    }

      public void delete (TaskType taskType) {
        TaskType t = em.merge (taskType);
        em.remove( t );
        em.flush();
      }

      public void update (TaskType taskType) {
        em.merge (taskType);
        em.flush();
      }

      public List <TaskType> findTaskTypes () {
    	  return (List <TaskType>) em.createQuery("select t from TaskType t").getResultList();
      }

      public List <TaskType> findActiveTaskTypes () {
    	  return (List <TaskType>) em.createQuery("select t from TaskType t where tst_isdeleted = 'O'").getResultList();
      }

      public TaskType findTaskType (int id) {
    	  return (TaskType) em.find(TaskType.class, id);
      }

	@Override
	public void logicalDelete(TaskType taskType) {
		TaskType t = em.merge (taskType);
        t.setTstIsdeleted((byte)1);
        update(t);
	}

}