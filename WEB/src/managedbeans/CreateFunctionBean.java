package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Function;
import com.cpeeterprise.BeanFunctionRemote;

@ManagedBean
@SessionScoped
public class CreateFunctionBean {
	
	@EJB
	public BeanFunctionRemote functionRemote;
	
	private String functionLabel = "";
	private int functionLevel;
	
	public void updateFunction()
	{
		Function function = new Function();
		function.setFctLabel(getFunctionLabel());
		function.setFctLevel(getFunctionLevel());
		functionRemote.update(function);
	}

	public String getFunctionLabel() {
		return functionLabel;
	}

	public void setFunctionLabel(String functionLabel) {
		this.functionLabel = functionLabel;
	}

	public int getFunctionLevel() {
		return functionLevel;
	}

	public void setFunctionLevel(int functionLevel) {
		this.functionLevel = functionLevel;
	}

	

}
