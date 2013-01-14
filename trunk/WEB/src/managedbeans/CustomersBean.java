package managedbeans;


import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Customer;

import com.cpeeterprise.BeanCustomerRemote;

@ManagedBean
@ApplicationScoped
public class CustomersBean {
	
	@EJB
	public BeanCustomerRemote customerRemote;
		
	public boolean deleteCustomer() {
		int cusId = Integer.parseInt((String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("customerIdDel"));
		Customer customer = customerRemote.findCustomer(cusId);
		customerRemote.logicalDelete(customer);
		return true;
	}
}