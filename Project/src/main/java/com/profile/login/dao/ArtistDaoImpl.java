package com.profile.login.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.commons.codec.binary.Base64;
import com.mysql.cj.jdbc.Blob;
import com.profile.login.beans.Artist;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.exception.ConstraintViolationException;


@Repository
public class ArtistDaoImpl implements ArtistDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void saveArtist(Artist artist) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(artist!=null) {
			try {
				session.save(artist);
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
	public Artist loginArtist(Artist artist) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String hql="from com.profile.login.beans.Artist as c where c.c_email=? and c.c_password=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,artist.getC_email());
				query.setParameter(1,artist.getC_password());
				artist=(Artist)query.uniqueResult();
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
			return artist;
		}

	@Override
	public void addPainting(Artist artist,Painting painting) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		System.out.println(painting.getImage());
		Blob blob =null;
		if(painting!=null && artist!=null) {
			try {
				painting.setArtists(artist);
				session.save(painting);
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
	public void editPainting(Artist artist,Painting painting) {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String hql="update com.profile.login.beans.Painting as c set c.Name=:name,c.artists.c_id=:artist,c.kind=:kind,c.length=:length where c.PaintingId=:id"; 
		if(painting!=null) {
			try {
				System.out.println(painting.getName()+painting.getArtists().getC_id()+painting.getKind()+painting.getLength()+painting.getPaintingId());
				Query query=session.createQuery(hql);
				query.setParameter("name",painting.getName());
				query.setParameter("artist",painting.getArtists().getC_id());
				query.setParameter("kind",painting.getKind());
				query.setParameter("length",painting.getLength());
				query.setParameter("id",painting.getPaintingId());
				int result =query.executeUpdate();
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}
	}

	@Override
	public Painting getPainting(String PaintingName) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String hql="From com.profile.login.beans.Painting as c where c.Name=?";
		Painting painting= null;
		if(PaintingName!=null) {
			try {
				Query query=session.createQuery(hql);
				query.setParameter(0,PaintingName);
				painting=(Painting)query.uniqueResult();
				if(painting == null) {
					throw new Exception();
				}
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
				throw new Exception();
			}
	}
		return painting;
	}

	@Override
	public void deletePainting(String paintingName) {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		String hql="Delete from com.profile.login.beans.Painting as c where c.Name=?";
		if(paintingName!=null) {
			try {
				Query query=session.createQuery(hql);
				query.setParameter(0,paintingName);
				query.executeUpdate();
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
	}
	}
	@Override
	public List<Painting> getArtistPainting(Painting painting) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Painting> view=null;
		String hql="from com.profile.login.beans.Painting as c where c.artists.c_id=?";
		try {	
				Query query=session.createQuery(hql);
				int i=1;
				query.setParameter(0,painting.getArtists().getC_id());
				view=query.list();
				 int v= view.size();
				for(int i1 =0;i1<v;i1++) {
					 byte[] base64Encoded = Base64.encodeBase64(view.get(i1).getImage());
					String blob =new String(base64Encoded,"UTF-8");
					 view.get(i1).setEncodedImage(blob);
				}
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
			return view;
	}

	@Override
	public List<Painting> getAllPainting() throws UnsupportedEncodingException {
		List<Painting> p =sessionFactory.openSession().createQuery("From Painting").list();
		int v= p.size();
		for(int i1 =0;i1<v;i1++) {
			 byte[] base64Encoded = Base64.encodeBase64(p.get(i1).getImage());
			String blob =new String(base64Encoded,"UTF-8");
			 p.get(i1).setEncodedImage(blob);
		}
		return p;
	}

	@Override
	public Artist getByArtist(String Artistname) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		Artist artist=null;
		String hql="from com.profile.login.beans.Artist as c where c.c_name=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,Artistname);
				artist=(Artist)query.uniqueResult();
				tx.commit();
				session.close();
			}catch (Exception e) {
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
			return artist;
		}
	
	@Override
	public void addMessage(Message message) throws Exception {
		Session session= sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		if(message!=null) {
			try {
				System.out.println("here");
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
	public List<Message> retrieveMessages(int artistID) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Message> message=null;
		String hql="from com.profile.login.beans.Message as c where c.artist.c_id=? and c.sentBy=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,artistID);
				query.setParameter(1,"Customer");
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
	public List<Message> retrieveSentMessages(int artistID) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		List<Message> message=null;
		String hql="from com.profile.login.beans.Message as c where c.artist.c_id=? and c.sentBy=?";
		try {
				Query query=session.createQuery(hql);
				query.setParameter(0,artistID);
				query.setParameter(1,"Artist");
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
	public List<Painting> orderByAsc() throws UnsupportedEncodingException{
		Session session=sessionFactory.openSession();
		Criteria crit =session.createCriteria(Painting.class);
		crit.addOrder(Order.asc("Name"));
		List<Painting> results =crit.list();
		int v= results.size();
		for(int i1 =0;i1<v;i1++) {
			 byte[] base64Encoded = Base64.encodeBase64(results.get(i1).getImage());
			String blob =new String(base64Encoded,"UTF-8");
			 results.get(i1).setEncodedImage(blob);
		}
		return results;
	}
	
	@Override
	public List<Painting> search(String search) throws UnsupportedEncodingException{
		Session session=sessionFactory.openSession();
		Criteria crit=session.createCriteria(Painting.class);
		Painting examplepainting =new Painting();
		examplepainting.setKind(search);
		Example example=Example.create(examplepainting);
		crit.add(example);
		List<Painting> results =crit.list();
		int v= results.size();
		for(int i1 =0;i1<v;i1++) {
			 byte[] base64Encoded = Base64.encodeBase64(results.get(i1).getImage());
			String blob =new String(base64Encoded,"UTF-8");
			 results.get(i1).setEncodedImage(blob);
		}
		return results;
	}
	}