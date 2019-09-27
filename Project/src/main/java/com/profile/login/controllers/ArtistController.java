package com.profile.login.controllers;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.profile.login.beans.Artist;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;
import com.profile.login.services.ArtistService;
import com.profile.login.services.CustomerService;
import com.profile.login.validation.ArtistValidation;
import com.profile.login.validation.MessageValidation;
import com.profile.login.validation.PaintingValidation;

@Controller
@RequestMapping(value="/artist")
public class ArtistController {
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String show(ModelMap model,HttpSession session) {
		if(session.getAttribute("artist")==null){
			model.put("customerData",new Artist());
			return "login/login";
		}else {
		return "redirect:painting";
	}

    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(ModelMap model,@ModelAttribute("customerData")Artist artist,HttpSession session) {
		if(artist.getC_email()!=null && artist.getC_password()!=null && session.getAttribute("artist")==null) {
			artist = artistService.loginArtist(artist);
			if(artist!=null) {
				session.setAttribute("artist", artist);
				return "redirect:painting";
			}else {
				System.out.println("here");
				model.put("failed","Login failed.");
				return "login/login";
			}
		}else {
			System.out.println("here");
			System.out.println(session.getAttribute("artist"));
			model.put("failed","Login failed.");
			return "login/login";
		}
		
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logOut(ModelMap model,HttpSession session) {
		session.removeAttribute("artist");
		return "redirect:login";
	}

	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showForm( ModelMap model) {
		model.put("customerData",new Artist());
		return "register/register";
	}
	

	@RequestMapping(value="/artistSuccess",method=RequestMethod.GET)
	public String showSuccess(ModelMap model) {
		model.put("customerData", new Artist());
		return "artistSuccess";
		}

	@RequestMapping(value="/Addpainting",method=RequestMethod.GET)
	public String showPainting(ModelMap model) {
		model.put("painting", new Painting());
		return "Addpainting";
		}
	

	@RequestMapping(value="/views",method=RequestMethod.GET)
	public String showView(ModelMap model) {
		model.put("painting", new Painting());
		return "view";
		}

	
	@RequestMapping(value="/edits",method=RequestMethod.GET)
	public String showEdits(ModelMap model) {
		model.put("painting", new Painting());
		return "edits";
		}
	

	@RequestMapping(value="/searchs",method=RequestMethod.GET)
	public String showSearchs(ModelMap model) {
		model.put("painting", new Painting());
		return "searchs";
		}
	
	@RequestMapping(value="/seeMessages",method=RequestMethod.GET)
	public String showMessages(ModelMap model) {
		model.put("message", new Message());
		return "seeMessages";
		}
	
	@RequestMapping(value="/successEdit",method=RequestMethod.GET)
	public String showEdit(ModelMap model) {
		return "successEdit";
		}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String saveForm(ModelMap model, @ModelAttribute("customerData")@Valid Artist artist,BindingResult br,HttpSession session){
		ArtistValidation artistValidation =new ArtistValidation();
		artistValidation.validate(artistValidation, br);
		if(br.hasErrors()) {
			return "register/register";
		}else {
			try {
			artistService.saveArtist(artist);
			session.setAttribute("artist",artist);
			return "redirect:artistSuccess";
			}catch(Exception e){
				model.put("failed","Register failed.");
				return "register/register";
			}
		}
	}
	
	@RequestMapping(value="/painting",method=RequestMethod.GET)
	public String setForm(Map<String,Object> map) throws UnsupportedEncodingException {
		Painting painting = new Painting();
		map.put("painting",painting);
		map.put("paintingList",artistService.getAllPainting());
		return "painting";
	}	
	
	@RequestMapping(value="/painting",method=RequestMethod.POST)
	public String doActions(ModelMap model,@ModelAttribute("painting")@Valid Painting painting,BindingResult result,@RequestParam("file")@Valid File file,@RequestParam String action,Map<String,Object> map,HttpSession session,HttpServletRequest req,HttpServletResponse res) throws Exception{
		Painting paintingResult	 = new Painting();
		switch(action.toLowerCase()) {
		case "add":
			PaintingValidation paintingValidation =new PaintingValidation();
			paintingValidation.validate(paintingValidation, result);
			if(result.hasErrors()) {
				System.out.println("here");
				System.out.println(file.length());
				return "painting";
			}
			try {
			painting.setArtists((Artist) session.getAttribute("artist"));
			File f = new File("C:\\Users\\vaniv\\OneDrive\\Pictures\\Saved Pictures\\"+file);
			if (f.exists()) {
				try{
					 BufferedImage bufferedImage=ImageIO.read(f);
			            ByteArrayOutputStream byteOutStream=new ByteArrayOutputStream();
			            ImageIO.write(bufferedImage, "jpg", byteOutStream);
			            byte[] b = byteOutStream.toByteArray();
			            painting.setImage(b);
				}catch(Exception e) {
					e.printStackTrace();
					}
				}
			artistService.addPainting(painting.getArtists(),painting);
			return "redirect:Addpainting";
			}catch(Exception e){
				model.put("failed","Painting already exists");
				return "painting";
			}
		case "toedit":
			System.out.println(painting.getName());
			session.setAttribute("paintings",painting);
			System.out.println("here");
			 return "redirect:edits";
		case "edit":
			PaintingValidation paintingValidation1 =new PaintingValidation();
			paintingValidation1.validate(paintingValidation1, result);
			if(result.hasErrors()) {
				return "edits";
			}
			painting.setArtists((Artist) session.getAttribute("artist"));
			artistService.editPainting(painting.getArtists(),painting);
			return "successEdit";
		case "delete":
			artistService.deletePainting(painting.getName());
			return "redirect:painting";
		case "sentmessage":
			Artist artist1 =(Artist) session.getAttribute("artist");
			List<Message> m1 =artistService.retrieveSentMessages(artist1.getC_id());
			System.out.println("here");
			session.setAttribute("sent",m1);
			return "sentMessages";
		case "searchs":
			session.setAttribute("paintings",painting);
			return "redirect:searchs";
		case "search":
			try {
			Painting searchedPainting = artistService.getPainting(painting.getName());
			session.setAttribute("search",searchedPainting);
			return "redirect:edits";}catch(Exception e) {
				model.put("failed","Please provide a valid name");
				return "searchs";
			}
		case "view":
			painting.setArtists((Artist)session.getAttribute("artist"));
			List<Painting> view=artistService.getArtistPainting(painting);
			session.setAttribute("view",view);
			return "redirect:views";	
		case "messages":
			Artist artist =(Artist) session.getAttribute("artist");
			List<Message> m =artistService.retrieveMessages(artist.getC_id());
			System.out.println(artist.getC_id());
			session.setAttribute("mess",m);
			return "redirect:seeMessages";
		}
		map.put("painting",paintingResult);
		map.put("paintingList",artistService.getAllPainting());
		return "painting";
	}
	
	 @RequestMapping(value="/messag",params="aname",method=RequestMethod.GET)
		public String showMessage(@RequestParam("aname") String aname,@RequestParam("pname") String pname,ModelMap model,HttpSession session) {
			session.setAttribute("currentCustomer",aname);
			session.setAttribute("currentPainting", pname);
			return "redirect:messages";
		}
	 @RequestMapping(value="/messages",method=RequestMethod.GET)
		public String showMessage(ModelMap model,HttpSession session) {
			model.put("message1", new Message());
			System.out.println("here");
			return "messages";
		}
	 
	 @RequestMapping(value="/sentMessages",method=RequestMethod.GET)
		public String showSentMessage(ModelMap model,HttpSession session) {
			model.put("message1", new Message());
			System.out.println("here");
			return "sentMessages";
		}
	 
	 @RequestMapping(value="/sents",method=RequestMethod.GET)
		public String showSent(ModelMap model,HttpSession session) {
			System.out.println("here");
			return "sents";
		}
	 
	@RequestMapping(value="/mess",method=RequestMethod.POST)
		public String doActions(ModelMap model,@ModelAttribute("message1")@Valid Message message,BindingResult result,@RequestParam String action,Map<String,Object> map,HttpSession session,HttpServletRequest req) throws Exception {
			Message paintingResult	 = new Message();
			switch(action.toLowerCase()) {
			case "send":
				message.setCustomer(customerService.getByCustomer((String)session.getAttribute("currentCustomer")));
				message.setArtist((Artist)session.getAttribute("artist"));
				message.setPainting(artistService.getPainting((String)session.getAttribute("currentPainting")));
				message.setSentBy("Artist");
				try {
				artistService.addMessage(message);
				return "redirect:sents";
				}catch(Exception e) {
					model.put("failed","Please enter message.");
					return "messages";
				}
		}
			return "messages";
	}
}
