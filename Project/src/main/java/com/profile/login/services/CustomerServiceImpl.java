package com.profile.login.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.profile.login.beans.Customer;
import com.profile.login.beans.Message;
import com.profile.login.dao.CustomerDao;


public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	@Override
	public void saveCustomer(Customer customer) throws Exception  {
		customerDao.saveCustomer(customer);
	}

	@Override
	public Customer loginCustomer(Customer customer) {
		return customerDao.loginCustomer(customer);
	}
	
	@Transactional
	public List<Message> retrieveSentMessages(int customerID){
		return customerDao.retrieveSentMessages(customerID);
	}

	@Transactional
	public void addMessage(Message message) throws Exception{
	  customerDao.addMessage(message);
		
	}
	
	@Transactional
	public List<Message> retrieveMessages(int customerID){
		return customerDao.retrieveMessages(customerID);
	}
	
	@Transactional
	public Customer getByCustomer(String Customername) {
		return customerDao.getByCustomer(Customername);
	}
}
