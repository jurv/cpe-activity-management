package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Message;

@Remote
public interface BeanMessageRemote {

	public void persist (Message message);
	public void delete (Message message);
	public void update (Message message);
	public List <Message> findMessages ();
	public Message findMessage (String id);
}
