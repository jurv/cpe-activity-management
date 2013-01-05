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
	
	public void updateCustomer()
	{
		Customer customer = new Customer();
		customer.setCusName("MonNom");
		customerRemote.update(customer);
	}

}
