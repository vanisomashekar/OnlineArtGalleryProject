package com.profile.login.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;
import com.profile.login.dao.ArtistDao;

@Service
public class ArtistServiceImpl implements ArtistService{

	@Autowired
	private ArtistDao artistDao;
	
	
	private static EntityManager entityManager;
	

	public void setArtistDao(ArtistDao artistDao) {
		this.artistDao = artistDao;
	}
	@Override
	public void saveArtist(Artist artist) throws Exception {
		artistDao.saveArtist(artist);
		
	}

	@Override
	public Artist loginArtist(Artist artist) {
		return artistDao.loginArtist(artist);
	}
	@Transactional
	public void addPainting(Artist artist,Painting painting) throws Exception {
         artistDao.addPainting(artist,painting);
	}
	@Transactional
	public void editPainting(Artist artist,Painting painting) {
		artistDao.editPainting(artist,painting);
		
	}
	@Transactional
	public Painting getPainting(String PaintingName) throws Exception {
		return artistDao.getPainting(PaintingName);
	}
	@Transactional
	public void deletePainting(String PaintingName) {
		artistDao.deletePainting(PaintingName);
	}
	
	@Transactional
	public List<Painting> getAllPainting() throws UnsupportedEncodingException {
		return artistDao.getAllPainting();
	}
	
	@Transactional
	public List<Message> retrieveSentMessages(int artistID){
		return artistDao.retrieveSentMessages(artistID);
	}
	
	@Transactional
	public List<Painting> orderByAsc()throws UnsupportedEncodingException{
		return artistDao.orderByAsc();
	}
	
	@Transactional
	public List<Painting> search(String search) throws UnsupportedEncodingException{
		return artistDao.search(search);
	}
	
	@Transactional
	public void addMessage(Message message) throws Exception{
		artistDao.addMessage(message);
	}
	@Transactional
	public List<Painting> getArtistPainting(Painting painting){
		return artistDao.getArtistPainting(painting);
	}
	@Transactional
	public Artist getByArtist(String Artistname) {
		return artistDao.getByArtist(Artistname);
	}
	@Transactional
	public List<Message> retrieveMessages(int artistID) {
		return artistDao.retrieveMessages(artistID);
	}
	
}
