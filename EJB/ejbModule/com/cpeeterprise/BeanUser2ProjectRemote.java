package com.cpeeterprise;

import java.util.List;
import javax.ejb.Remote;
import model.User2Project;

@Remote
public interface BeanUser2ProjectRemote {
	public User2Project persist (User2Project user2project);
	public void delete (User2Project user2project);
	public void update (User2Project user2project);
}