package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Task;

@Remote
public interface BeanTaskRemote {

	public Task persist (Task task);
	public void delete (Task task);
	public void update (Task task);
	public List <Task> findTasks ();
	public List <Task> findActiveTasks ();
	public Task findTask (int id);
	public List <Task> findTasksByUser (int userId);
	public List <Task> findTasksByProject (int prjId,int staId);
	public List <Task> findTasksByProject (int prjId);
	public void logicalDelete(Task task);
}
