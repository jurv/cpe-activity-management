package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.WorkPiece;

/**
 * Session Bean implementation class BeanProject
 */
@Stateful(mappedName = "WorkPiece")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeanWorkPiece implements BeanWorkPieceRemote {
	
	@PersistenceContext
	private EntityManager em;
	
    public WorkPiece persist (WorkPiece workPiece) {
        em.persist (workPiece);
        em.flush();
        em.refresh(workPiece);
        return workPiece;
    }

  public void delete (WorkPiece workPiece) {
    WorkPiece t = em.merge (workPiece);
    em.remove( t );
    em.flush();
  }

  public void update (WorkPiece workPiece) {
    em.merge (workPiece);
    em.flush();
  }

  public List <WorkPiece> findWorkPieces () {
	  return (List <WorkPiece>) em.createQuery("select wp from WorkPiece wp").getResultList();
  }

  public WorkPiece findWorkPiece (int id) {
	  return (WorkPiece) em.find(WorkPiece.class, id);
  }
  
  public List <WorkPiece> findWorkPiecesByUser (int userId) {
	  return (List <WorkPiece>) em.createQuery("select wp from WorkPiece wp where usr_id = '" + userId + "'").getResultList();
  }
}