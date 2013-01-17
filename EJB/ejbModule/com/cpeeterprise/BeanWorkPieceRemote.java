package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.WorkPiece;

@Remote
public interface BeanWorkPieceRemote {

	public WorkPiece persist (WorkPiece workPiece);
	public void delete (WorkPiece workPiece);
	public void update (WorkPiece workPiece);
	public List <WorkPiece> findWorkPieces ();
	public WorkPiece findWorkPiece (int id);
	public List <WorkPiece> findWorkPiecesByTask (int tskId);
	public List <WorkPiece> findWorkPiecesByUser (int userId);
}
