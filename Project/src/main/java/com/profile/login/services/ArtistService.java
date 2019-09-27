package com.profile.login.services;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ArtistService {
	public void saveArtist(Artist artist) throws Exception;
	public Artist loginArtist(Artist artist);
	public void addPainting(Artist artist,Painting painting) throws Exception;
	public void editPainting(Artist artist,Painting painting);
	public Painting getPainting(String PaintingName) throws Exception;
	public void deletePainting(String paintingName);
	public Artist getByArtist(String Artistname);
	public List<Painting> getAllPainting() throws UnsupportedEncodingException;
	public List<Message> retrieveMessages(int artistID);
	public List<Painting> getArtistPainting(Painting painting);
	public void addMessage(Message message) throws Exception;
	public List<Message> retrieveSentMessages(int artistID);
	public List<Painting> orderByAsc() throws UnsupportedEncodingException;
	public List<Painting> search(String search) throws UnsupportedEncodingException;
}
