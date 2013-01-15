package managedbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.Customer;

import com.cpeeterprise.BeanCustomerRemote;

@ManagedBean
@RequestScoped
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