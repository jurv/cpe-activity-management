package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Project;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "Project")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanProject implements BeanProjectRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public void persist (Project project) {
        em.persist (project);
        em.flush();
        em.refresh(project);
      }

      public void delete (Project project) {
        Project t = em.merge (project);
        em.remove( t );
        em.flush();

      }

      public void update (Project project) {
        em.merge (project);
        em.flush();
        em.refresh(project);
      }

      public List <Project> findProjects () {
    	  return (List <Project>) em.createQuery("select t from Project t").getResultList();
      }

      public Project findProject (String id) {
    	  return (Project) em.find(Project.class, Long.parseLong(id));
      }
      

}