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
	public List<User> findUsersByProject(int prjId);
	public int connectUser(String login, String password);
	public boolean disconnectUser(User user);
	public List <User> getConnectedUsers ();
	public List <User> findCdps();
	public List<User> findActiveCdps();
	public List<User> findUserWithChatConv(int usrId, int prjId);
	public List<User> findUserWithChatConv(int usrId);
	public void logicalDelete(User usr);
	public List<User> findActiveUsers();
}
