package managedbeans;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Function;
import model.Task;
import model.TaskStatus;
import model.WorkPiece;
import model.User;


import com.cpeeterprise.BeanFunctionRemote;
import com.cpeeterprise.BeanTaskRemote;
import com.cpeeterprise.BeanTaskStatusRemote;
import com.cpeeterprise.BeanUserRemote;
import com.cpeeterprise.BeanWorkPieceRemote;

@ManagedBean
@SessionScoped
public class DashboardBean {
	
	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanFunctionRemote functionRemote;
	@EJB
	public BeanWorkPieceRemote workPieceRemote;
	@EJB
	public BeanTaskRemote taskRemote;
	@EJB
	public BeanTaskStatusRemote taskStatusRemote;
	
	private User currentUser;
	private boolean dataLoaded = false;
	private ArrayList <ObjectOutput> userFunctions = new ArrayList <ObjectOutput>(); 
	private ArrayList <ObjectOutput> userWrkAmount = new ArrayList <ObjectOutput>(); 
	private ArrayList <ObjectOutput> userTasks = new ArrayList <ObjectOutput>();
	
	public class ObjectOutput implements Comparable<ObjectOutput> {
		private int count = 0;
		private String label = "";
		public ObjectOutput(int pcount, String pLabel) {
			this.count = pcount;
			this.label = pLabel;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		
		@Override
		public boolean equals (Object o) {
			return (getLabel() == ((ObjectOutput)o).getLabel());
		}
		
		@Override
		public int compareTo(ObjectOutput o) {
			return getLabel().compareTo(o.getLabel());
		}
	}
	
	public void initView () {
		
		if(dataLoaded == false) {
			
			// On remplit les décomptes de tâches pour l'utilisateur courant
			this.getUserTasks().clear();
			this.getUserTasks().addAll(getUserTasksOutput());
			
			// On remplit les décomptes des fonctions au sein des projets
			this.userFunctions.clear();
			this.userFunctions.addAll(getUserFunctionsOutput());
			
			// On remplit les temps de travail de l'utilisateur
			this.userWrkAmount.clear();
			this.userWrkAmount.addAll(getUserWorkAmountOutput());
			Collections.sort(this.userWrkAmount);
			
			dataLoaded = true;
		}
	}
	
	public ArrayList <ObjectOutput> getUserFunctionsOutput() {
		LinkedHashMap <String, Integer> fctsMap = new LinkedHashMap<String, Integer>(); 
		List<Function> usrFcts = functionRemote.findAllFunctionsForUser(this.currentUser.getUsrId());
		List<Function> allFcts = functionRemote.findFunction();
		ArrayList<ObjectOutput> fcts = new ArrayList<ObjectOutput>();
		
		for(Function f : usrFcts) {
			Integer occ = fctsMap.get(f.getFctLabel());
			if(occ == null) {
				fctsMap.put(f.getFctLabel(), 1);
			}
			else {
				fctsMap.remove(f.getFctLabel());
				fctsMap.put(f.getFctLabel(), occ++);
			}
		}
		for(Function f : allFcts) {
			Integer occ = fctsMap.get(f.getFctLabel());
			if(occ == null) {
				fctsMap.put(f.getFctLabel(), 0);
			}
		}
		for(String i : fctsMap.keySet()) {
			fcts.add(new ObjectOutput(fctsMap.get(i), i));
		}
		
		return fcts;
	}
	
	public ArrayList <ObjectOutput> getUserWorkAmountOutput() {
		LinkedHashMap <String, Integer> wpsMap = new LinkedHashMap<String, Integer>(); 
		ArrayList<ObjectOutput> wrkAmount = new ArrayList<ObjectOutput>();
		ArrayList<WorkPiece> wps = new ArrayList<WorkPiece>();
		wps.addAll(this.workPieceRemote.findWorkPiecesByUser(this.currentUser.getUsrId()));
		
		for(WorkPiece f : wps) {
			Integer occ = wpsMap.get(f.getWrkDate().toString());
			if(occ == null) {
				wpsMap.put(f.getWrkDate().toString(), f.getWrkDuration());
			}
			else {
				wpsMap.remove(f.getWrkDate().toString());
				wpsMap.put(f.getWrkDate().toString(), occ + f.getWrkDuration());
			}
		}
		
		for(String i : wpsMap.keySet()) {
			wrkAmount.add(new ObjectOutput(wpsMap.get(i), i));
		}
		
		return wrkAmount;
	}
	
	public ArrayList <ObjectOutput> getUserTasksOutput() {
		LinkedHashMap <String, Integer> tsksMap = new LinkedHashMap<String, Integer>(); 
		ArrayList<ObjectOutput> tsksOutput = new ArrayList<ObjectOutput>();
		ArrayList<Task> tsks = new ArrayList<Task>();
		ArrayList<TaskStatus> tskss = new ArrayList<TaskStatus>();
		tsks.addAll(this.taskRemote.findTasksByUser(this.currentUser.getUsrId()));
		tskss.addAll(taskStatusRemote.findTaskStatus());
		
		for(Task f : tsks) {
			Integer occ = tsksMap.get(f.getTskId());
			String value = "";
			for(TaskStatus tss : tskss) {
				if(tss.getTssId() == f.getTssId())
					value = tss.getTssLabel();
			}
			if(occ == null) {
				tsksMap.put(value, 1);
			}
			else {
				tsksMap.remove(value);
				tsksMap.put(value, occ ++);
			}
		}
		
		for(TaskStatus f : tskss) {
			Integer occ = tsksMap.get(f.getTssLabel());
			if(occ == null) {
				tsksMap.put(f.getTssLabel(), 0);
			}
		}
		
		for(String i : tsksMap.keySet()) {
			tsksOutput.add(new ObjectOutput(tsksMap.get(i), i));
		}
		
		return tsksOutput;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public ArrayList <ObjectOutput> getUserFunctions() {
		return userFunctions;
	}

	public void setUserFunctions(ArrayList <ObjectOutput> userFunctions) {
		this.userFunctions = userFunctions;
	}

	public ArrayList <ObjectOutput> getUserWrkAmount() {
		return userWrkAmount;
	}

	public void setUserWrkAmount(ArrayList <ObjectOutput> userWrkAmount) {
		this.userWrkAmount = userWrkAmount;
	}

	public ArrayList <ObjectOutput> getUserTasks() {
		return userTasks;
	}

	public void setUserTasks(ArrayList <ObjectOutput> userTasks) {
		this.userTasks = userTasks;
	}
}
