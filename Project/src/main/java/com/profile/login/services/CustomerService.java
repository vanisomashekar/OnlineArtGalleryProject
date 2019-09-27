package com.profile.login.services;

import java.util.List;

import com.profile.login.beans.Customer;
import com.profile.login.beans.Message;

public interface CustomerService {
	public void saveCustomer(Customer customer)throws Exception ;
	public Customer loginCustomer(Customer customer);
	public void addMessage(Message message)throws Exception;  
	public List<Message> retrieveMessages(int customerID);
	public Customer getByCustomer(String Customername);
	public List<Message> retrieveSentMessages(int customerID);
}
