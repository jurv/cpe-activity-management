package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.TaskStatus;

@Remote
public interface BeanTaskStatusRemote {

	public TaskStatus persist (TaskStatus task);
      public void delete (TaskStatus task);
      public void update (TaskStatus task);
      public List <TaskStatus> findTaskStatus ();
      public TaskStatus findTaskStatus (int id);
      public void logicalDelete(TaskStatus task);
}
