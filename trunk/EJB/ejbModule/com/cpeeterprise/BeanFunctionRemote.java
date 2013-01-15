package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Function;

@Remote
public interface BeanFunctionRemote {

	public void persist (Function function);
	public void delete (Function function);
	public void update (Function function);
	public List <Function> findFunction ();
	public Function findFunction (int id);
	public Function findFunction (String id);
	public void logicalDelete(Function fct);
}
