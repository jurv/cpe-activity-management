package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Message;
import model.Project;

@Remote
public interface BeanProjectRemote {

	public Project persist (Project project);
	public void delete (Project project);
	public void update (Project project);
	public List <Project> findProjects ();
	public List <Project> findActiveProjects ();
	public Project findProject (int id);
	public List <Project> findProjectsByUser (int userId);
	public List <Project> findManagedProjectsByUser (int userId);
	public void logicalDelete(Project project);
}
