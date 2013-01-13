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
      }

      public List <Project> findProjects () {
    	  return (List <Project>) em.createQuery("select t from Project t").getResultList();
      }

      public Project findProject (String id) {
    	  return (Project) em.find(Project.class, Long.parseLong(id));
      }
      
      public List <Project> findProjectsByUser (int userId) {
    	  return (List <Project>) em.createQuery("select p from Project p where prj_id IN ( select up from User2Project up where usr_id = " + userId + ")").getResultList();
      }

}