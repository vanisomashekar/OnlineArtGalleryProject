package com.profile.login.dao;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ArtistDao {
	public void saveArtist(Artist artist) throws Exception;
	public Artist loginArtist(Artist artist);
	public Artist getByArtist(String Artistname);
	public void addPainting(Artist artist,Painting painting) throws Exception;
	public void editPainting(Artist artist,Painting painting);
	public Painting getPainting(String PaintingName) throws Exception;
	public void deletePainting(String paintingName);
	public List<Painting> getAllPainting() throws UnsupportedEncodingException;
	public List<Painting> getArtistPainting(Painting painting);
	public List<Message> retrieveMessages(int artistID);
	public void addMessage(Message message) throws Exception;
	public List<Message> retrieveSentMessages(int artistID);
	public List<Painting> orderByAsc() throws UnsupportedEncodingException;
	public List<Painting> search(String search) throws UnsupportedEncodingException;
}
