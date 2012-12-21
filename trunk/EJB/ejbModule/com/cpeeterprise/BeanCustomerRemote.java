package com.cpeeterprise;

import java.util.List;

import javax.ejb.Remote;

import model.Customer;

@Remote
public interface BeanCustomerRemote {

	public void persist (Customer customer);
	public void delete (Customer customer);
	public void update (Customer customer);
	public List <Customer> findCustomers ();
	public Customer findCustomer (String id);
}
