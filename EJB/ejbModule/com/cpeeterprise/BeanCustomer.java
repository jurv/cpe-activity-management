package com.cpeeterprise;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Customer;

/**
 * Session Bean implementation class BeanCustomer
 */
@Stateful(mappedName = "Customer")
@TransactionManagement(TransactionManagementType.BEAN)
public class BeanCustomer implements BeanCustomerRemote {
	
	  @PersistenceContext
	  private EntityManager em;
    /**
     * Default constructor. 
     */
    public BeanCustomer() {
        // TODO Auto-generated constructor stub
    }
    
    public void persist (Customer customer) {
        em.persist (customer);
      }

      public void delete (Customer customer) {
        Customer t = em.merge (customer);
        em.remove( t );

      }

      public void update (Customer customer) {
        em.merge (customer);
      }

      public List <Customer> findCustomers () {

        return (List <Customer>) em.createQuery("select t from Customer t")
                                      .getResultList();
      }

      public Customer findCustomer (String id) {

        return (Customer) em.find(Customer.class, Long.parseLong(id));
      }


}