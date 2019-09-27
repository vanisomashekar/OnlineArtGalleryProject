package com.profile.login.dao;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Customer;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;


public class  CustomerDaoImpl implements CustomerDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void saveCustomer(Customer customer) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(customer!=null) {
			try {
				session.save(customer);
				tx.commit();
				session.close();
			}catch(ConstraintViolationException e) {
				tx.rollback();
				session.close();
				throw  new Exception();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}

	}
	@Override
	public Customer loginCustomer(Customer customer) {
	Session session=sessionFactory.openSession();
	Transaction tx=session.beginTransaction();
	String hql="from com.profile.login.beans.Customer as c where c.c_email=? and c.c_password=?";
	try {
			Query query=session.createQuery(hql);
			query.setParameter(0,customer.getC_email());
			query.setParameter(1,customer.getC_password());
			customer=(Customer)query.uniqueResult();
			tx.commit();
			session.close();
		}catch (Exception e) {
			tx.rollback();
			session.close();
			e.printStackTrace();
		}
		return customer;
	}
	@Override
	public void addMessage(Message message) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(message!=null) {
			try {
				session.save(message);
				tx.commit();
				session.close();
			}catch(RuntimeException e) {
				tx.rollback();
				session.close();
				throw  new Exception();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}	
	}
	
	@Override
	public List<Message> retrieveMessages(int customerID) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Message> message=null;
		String hql="from com.profile.login.beans.Message as c where c.customer.c_id=? and c.sentBy=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,customerID);
				query.setParameter(1, "Artist");
				message=query.list();
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		return message;
		}
	
	@Override
	public List<Message> retrieveSentMessages(int customerID) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Message> message=null;
		String hql="from com.profile.login.beans.Message as c where c.customer.c_id=? and c.sentBy=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,customerID);
				query.setParameter(1, "Customer");
				message=query.list();
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		return message;
		}
	
	@Override
	public Customer getByCustomer(String Customername) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Customer customer=null;
		String hql="from com.profile.login.beans.Customer as c where c.c_name=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,Customername);
				customer=(Customer)query.uniqueResult();
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
			return customer;
		}
}
