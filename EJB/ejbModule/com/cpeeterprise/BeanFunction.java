package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Function;

/**
 * Session Bean implementation class BeanFunction
 */
@Stateful(mappedName = "Function")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanFunction implements BeanFunctionRemote {

	@PersistenceContext
	 private EntityManager em;
	 
    /**
     * Default constructor. 
     */
    public BeanFunction() {
        // TODO Auto-generated constructor stub
    }
    public void persist (Function function) {
        em.persist (function);
        em.flush();
        em.refresh(function);
      }

      public void delete (Function function) {
    	Function t = em.merge (function);
        em.remove( t );
        em.flush();

      }

      public void update (Function function) {
        em.merge (function);
        em.flush();
      }

      public List <Function> findFunction () {

        return (List <Function>) em.createQuery("select t from Function t").getResultList();
      }

      public Function findFunction (int id) {
    	  return (Function) em.find(Function.class, id);
      }
      
      public Function findFunction (String id) {

        return (Function) em.find(Function.class, Long.parseLong(id));
      }

	  public void logicalDelete(Function fct) {
		Function fonction = em.merge (fct);
		fonction.setFctIsdeleted((byte)1);
		update(fonction);
	  }

}
