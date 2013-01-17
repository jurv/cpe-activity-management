package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Task;
import model.TaskType;

@Remote
public interface BeanTaskTypeRemote {

	public TaskType persist (TaskType taskType);
	public void delete (TaskType taskType);
	public void update (TaskType taskType);
	public List <TaskType> findTaskTypes ();
	public List <TaskType> findActiveTaskTypes ();
	public TaskType findTaskType (int id);
	public void logicalDelete(TaskType taskType);
}
