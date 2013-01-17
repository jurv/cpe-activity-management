package managedbeans;

import java.io.Console;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUser2ProjectRemote;

import model.Function;
import model.Project;
import model.User;
import model.User2Project;

@ManagedBean
@SessionScoped
public class CreateProjectBean {
	
	// Constantes
	final int ID_CDP = 1;
	final int ID_DEP = 2;
	final int ID_DEV = 3;
	final int ID_TES = 4;
	final int ID_REL = 5;

	@EJB
	public BeanProjectRemote projectRemote;
	@EJB
	public BeanUser2ProjectRemote user2projectRemote;
	
	private User currentUser;
	private String projectName = "";
	private String projectComment = "";
	private Date projectDateCreated = new Date();
	private Date projectDateFinished = new Date();
	private int projectTotalTime = 0;
	private int projectCusId = 0;
	private int projectCdpId = 0;
	
	private List<String> listDev;
	private List<String> listTes;
	private List<String> listRel;
	
	public String updateProject()
	{
		
		String nextPage="Projects";
		Project project = new Project();
		User2Project user2projectCdp = new User2Project();
		User2Project user2projectDir = new User2Project();
		
		project.setPrjLabel(this.projectName);
		project.setPrjDateCreated(this.projectDateCreated);
		project.setPrjDateFinished(this.projectDateFinished);
		project.setPrjComment(this.projectComment);
		project.setCusId(this.projectCusId);
		project.setPrjNbDays(this.projectTotalTime);
		project = projectRemote.persist(project);
		
		//ajout chef de projet
		user2projectCdp.setPrjId(project.getPrjId());
		user2projectCdp.setFctId(ID_CDP);
		user2projectCdp.setUsrId(this.projectCdpId);
		user2projectRemote.persist(user2projectCdp);
		
		//ajout directeur de projet
		user2projectDir.setPrjId(project.getPrjId());
		user2projectDir.setFctId(ID_DEP);
		user2projectDir.setUsrId(this.currentUser.getUsrId());
		user2projectRemote.persist(user2projectDir);
		
		Iterator<String> itListDev = listDev.iterator(); 
		while(itListDev.hasNext()) {
		    int dev = Integer.parseInt(itListDev.next());
		    User2Project userDev2project = new User2Project();
		    userDev2project.setPrjId(project.getPrjId());
		    userDev2project.setFctId(ID_DEV);
		    userDev2project.setUsrId(dev);
			user2projectRemote.persist(userDev2project);
		}
		Iterator<String> itListRel = listRel.iterator(); 
		while(itListRel.hasNext()) {
		    int rel = Integer.parseInt(itListRel.next());
		    User2Project userRel2project = new User2Project();
		    userRel2project.setPrjId(project.getPrjId());
		    userRel2project.setFctId(ID_REL);
		    userRel2project.setUsrId(rel);
			user2projectRemote.persist(userRel2project);
		} 
		Iterator<String> itListTes = listTes.iterator(); 
		while(itListTes.hasNext()) {
			int rel = Integer.parseInt(itListTes.next());
		    User2Project userTes2project = new User2Project();
		    userTes2project.setPrjId(project.getPrjId());
		    userTes2project.setFctId(ID_TES);
		    userTes2project.setUsrId(rel);
			user2projectRemote.persist(userTes2project);
		} 
		return nextPage;
	}
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public int getProjectCusId() {
		return this.projectCusId;
	}

	public void setProjectCusId(int c) {
		this.projectCusId = c;
	}

	public int getProjectCdpId() {
		return projectCdpId;
	}

	public void setProjectCdpId(int projectCdpId) {
		this.projectCdpId = projectCdpId;
	}

	public String getProjectComment() {
		return projectComment;
	}

	public void setProjectComment(String projectComment) {
		this.projectComment = projectComment;
	}

	public Date getProjectDateCreated() {
		return projectDateCreated;
	}

	public void setProjectDateCreated(Date projectDateCreated) {
		this.projectDateCreated = projectDateCreated;
	}

	public Date getProjectDateFinished() {
		return projectDateFinished;
	}

	public void setProjectDateFinished(Date projectDateFinished) {
		this.projectDateFinished = projectDateFinished;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectTotalTime() {
		return projectTotalTime;
	}

	public void setProjectTotalTime(int projectTotalTime) {
		this.projectTotalTime = projectTotalTime;
	}

	public List<String> getListDev() {
		return listDev;
	}

	public void setListDev(List<String> lstDev) {
		this.listDev = lstDev;
	}
	
	public List<String> getListRel() {
		return listRel;
	}

	public void setListRel(List<String> lstRel) {
		this.listRel = lstRel;
	}
	
	public List<String> getListTes() {
		return listTes;
	}
	public void setListTes(List<String> lstTes) {
		this.listTes = lstTes;
	}
}

