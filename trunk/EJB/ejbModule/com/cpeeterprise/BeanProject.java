package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Message;
import model.Project;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "Project")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanProject implements BeanProjectRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public Project persist (Project project) {
        em.persist (project);
        em.flush();
        em.refresh(project);
        return project;
    }

      public void delete (Project project) {
        Project t = em.merge (project);
        em.remove( t );
        em.flush();
      }

      public void update (Project project) {
        em.merge (project);
        em.flush();
      }

      public List <Project> findProjects () {
    	  return (List <Project>) em.createQuery("select t from Project t").getResultList();
      }

      public List <Project> findActiveProjects () {
    	  return (List <Project>) em.createQuery("select t from Project t where prj_isdeleted = 'O'").getResultList();
      }

      public Project findProject (int id) {
    	  return (Project) em.find(Project.class, id);
      }
      
      public List <Project> findProjectsByUser (int userId) {
    	  return (List <Project>) em.createQuery("select p from Project p where prj_id IN ( select up from User2Project up where usr_id = '" + userId + "')").getResultList();
      }
      
      public List <Project> findManagedProjectsByUser (int userId) {
    	  return (List <Project>) em.createQuery("select p from Project p where prj_id IN ( select up from User2Project up where usr_id = '" + userId + "' and fct_id in (select fct from Function fct where fct_level >= 20))").getResultList();
      }

	@Override
	public void logicalDelete(Project project) {
		Project p = em.merge (project);
        p.setPrjIsdeleted((byte)1);
        update(p);
	}

}