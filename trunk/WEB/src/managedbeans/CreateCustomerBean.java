package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Customer;
import com.cpeeterprise.BeanCustomerRemote;

@ManagedBean
@SessionScoped
public class CreateCustomerBean {
	
	@EJB
	public BeanCustomerRemote customerRemote;
	private String customerName = "";
	
	public void updateCustomer()
	{
		Customer customer = new Customer();
		customer.setCusName(this.customerName);
		customerRemote.update(customer);
	}
	
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
