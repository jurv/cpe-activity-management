package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Project;

@Remote
public interface BeanProjectRemote {

	public void persist (Project project);
	public void delete (Project project);
	public void update (Project project);
	public List <Project> findProjects ();
	public Project findProject (String id);
}
