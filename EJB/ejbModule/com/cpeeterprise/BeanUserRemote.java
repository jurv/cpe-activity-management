package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.User;

@Remote
public interface BeanUserRemote {

	public void persist (User user);
	public void delete (User user);
	public void update (User user);
	public List <User> findUsers ();
	public User findUser (String id);
	public boolean connectUser(User user);
}