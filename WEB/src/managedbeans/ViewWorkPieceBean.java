package managedbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.WorkPiece;
import model.User;

import com.cpeeterprise.BeanMessageRemote;
import com.cpeeterprise.BeanProjectRemote;
import com.cpeeterprise.BeanUserRemote;
import com.cpeeterprise.BeanWorkPieceRemote;


@ManagedBean
@ViewScoped
public class ViewWorkPieceBean {

	@EJB
	public BeanUserRemote userRemote;
	@EJB
	public BeanWorkPieceRemote workPieceRemote;
	private User currentUser;
	private User wpUser;
	private WorkPiece wp;
	private boolean pageLoaded = false;
	
	public void initView () {
		if(!pageLoaded) {

			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String param = (String)params.get("wpId");
			Integer wpId = 0;
			wpId = Integer.parseInt(param);
			if(wpId > 0)
				this.wp = workPieceRemote.findWorkPiece(wpId);
			if(getWp() != null && getWp().getUsrId() > 0)
				this.wpUser = userRemote.findUser(getWp().getUsrId());
			pageLoaded = true;
		}	
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public WorkPiece getWp() {
		return wp;
	}

	public void setWp(WorkPiece wp) {
		this.wp = wp;
	}

	public User getWpUser() {
		return wpUser;
	}

	public void setWpUser(User wpUser) {
		this.wpUser = wpUser;
	}
}
