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
	public User findUser (int id);
	public int connectUser(String login, String password);
	public boolean disconnectUser(User user);
}
