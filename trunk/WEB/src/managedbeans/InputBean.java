package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import model.Customer;

import com.cpeeterprise.BeanCustomerRemote;

@ManagedBean(name="input")
@SessionScoped
public class InputBean {
	
	@EJB
	public BeanCustomerRemote customerRemote;
	
	private List<SelectItem> customersItems = new ArrayList<SelectItem>();
	private int customerValue;
	
	public List<SelectItem> getCustomersItems() {
		if(this.customersItems.isEmpty()){
			List<Customer> customers = customerRemote.findCustomers();
			for(Customer c:customers){
				customersItems.add(new SelectItem(c.getCusId(), c.getCusName()));
			}
		}
		return customersItems;
	}

	public int getCustomerValue() {
		return this.customerValue;
	}

	public void setCustomerValue(int c) {
		this.customerValue = c;
	}
}
