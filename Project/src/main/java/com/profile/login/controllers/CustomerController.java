package com.profile.login.controllers;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.profile.login.beans.Artist;
import com.profile.login.beans.Customer;
import com.profile.login.beans.Message;
import com.profile.login.beans.Painting;
import com.profile.login.services.ArtistService;
import com.profile.login.services.CustomerService;
import com.profile.login.validation.CustomerValidation;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/gallery",method=RequestMethod.GET)
	public String showGallery(ModelMap model) {
		model.put("message1", new Message());
		return "gallery";
		}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String show(ModelMap model,HttpSession session) {
		if(session.getAttribute("customer")==null){
			model.put("customerData",new Customer());
			return "login/login";
		}else {
		return "redirect:gallery";
	}

    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String doLogin(ModelMap model,@ModelAttribute("customerData")Customer customer,HttpSession session) throws UnsupportedEncodingException {
		if(customer.getC_email()!=null && customer.getC_password()!=null && session.getAttribute("customer")==null) {
			customer = customerService.loginCustomer(customer);
			if(customer!=null) {
				session.setAttribute("customer", customer);
				List<Painting> view=artistService.getAllPainting();
				session.setAttribute("views",view);
				return "redirect:gallery";
			}else {
				model.put("failed","Login failed.");
				return "login/login";
			}
		}else {
			model.put("failed","Login failed.");
			return "login/login";
		}
		
	}
	
	
	
	 @RequestMapping(value="/messag",params="aname",method=RequestMethod.GET)
	public String showMessage(@RequestParam("aname") String aname,@RequestParam("pname") String pname,ModelMap model,HttpSession session) {
		session.setAttribute("Currentartist",aname);
		session.setAttribute("currentPainting", pname);
		return "redirect:message";
	}
	
	@RequestMapping(value="/message",method=RequestMethod.GET)
	public String showMessage(ModelMap model,HttpSession session) {
		model.put("message1", new Message());
		System.out.println("here");
		return "message";
	}
	
	@RequestMapping(value="/sentMessage",method=RequestMethod.GET)
	public String showSentMessages(ModelMap model,HttpSession session) {
		model.put("message1", new Message());
		System.out.println("here");
		return "sentMessage";
	}
	@RequestMapping(value="/showMessages",method=RequestMethod.GET)
	public String Messages(ModelMap model,HttpSession session) {
		model.put("message1", new Message());
		return "showMessages";
	}
	
	@RequestMapping(value="/mess",method=RequestMethod.POST)
	public String doActions(ModelMap model,@ModelAttribute("message1")@Valid Message message,BindingResult result,@RequestParam String action,Map<String,Object> map,HttpSession session,HttpServletRequest req) throws Exception {
		Message paintingResult	 = new Message();
		switch(action.toLowerCase()) {
		case "send":
			message.setCustomer((Customer)session.getAttribute("customer"));
			message.setArtist(artistService.getByArtist((String)session.getAttribute("Currentartist")));
			message.setPainting(artistService.getPainting((String)session.getAttribute("currentPainting")));
			message.setSentBy("Customer");
			try {
			customerService.addMessage(message);
			return "redirect:gallery";
		}catch(Exception e) {
			model.put("failed","Please enter message.");
			return "message";
		}
		case "recievedmessages":
			Customer customer = (Customer)session.getAttribute("customer");
			List<Message> m = customerService.retrieveMessages(customer.getC_id());
			session.setAttribute("customerMessages", m);
			return "redirect:showMessages";
		case "sentmessages":
			Customer customers = (Customer)session.getAttribute("customer");
			List<Message> m1 = customerService.retrieveSentMessages(customers.getC_id());
			session.setAttribute("sents",m1);
			return "redirect:sentMessage";
		case "sortascending":
			List<Painting> p= artistService.orderByAsc();
			session.setAttribute("views", p);
			return "redirect:gallery";
		case "search":
			System.out.println(message.getSearch());
			List<Painting> p1 = artistService.search(message.getSearch());
			session.setAttribute("views", p1);
			return "redirect:gallery";
		}
		map.put("message",paintingResult);
		return "message";
	}
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logOut(ModelMap model,HttpSession session) {
		session.removeAttribute("customer");
		return "redirect:login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showForm( ModelMap model) {
		model.put("customerData",new Customer());
		return "register/register";
	}
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String showSuccess(ModelMap model) {
		model.put("customerData", new Customer());
		return "success";
		}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String saveForm(ModelMap model,@ModelAttribute("customerData")@Valid Customer customer,BindingResult br,HttpSession session) {
		CustomerValidation customerValidation =new CustomerValidation();
		customerValidation.validate(customerValidation, br);
		if(br.hasErrors()) {
			return "register/register";
		}else {
			try {
			customerService.saveCustomer(customer);
			session.setAttribute("customer",customer);
			return "redirect:success";
		}catch(Exception e){
			model.put("failed","Register failed.");
			return "register/register";
			}
		}
	}
	 
}
