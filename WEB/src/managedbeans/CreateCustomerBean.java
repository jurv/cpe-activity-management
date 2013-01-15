package managedbeans;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Customer;
import com.cpeeterprise.BeanCustomerRemote;

@ManagedBean
@RequestScoped
public class CreateCustomerBean {
	
	@EJB
	public BeanCustomerRemote customerRemote;
	private String customerName = "";
	
	public String updateCustomer()
	{
		String nextPage="Customers";
		Customer customer = new Customer();
		customer.setCusName(this.customerName);
		customerRemote.update(customer);
		return nextPage;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
