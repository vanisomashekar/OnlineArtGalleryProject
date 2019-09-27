package com.profile.login.dao;

import java.util.List;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Customer;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;

public interface CustomerDao {

	public void saveCustomer(Customer customer) throws Exception ;
	public Customer loginCustomer(Customer customer);
	public void addMessage(Message message) throws Exception; 
	public List<Message> retrieveMessages(int customerID);
	public Customer getByCustomer(String Customername);
	public List<Message> retrieveSentMessages(int customerID);
}
