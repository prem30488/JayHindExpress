package com.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.HeadingDAO;
import com.dao.HeadingDAOImpl;
import com.models.Heading;

@Controller
public class LoginController {

	@Autowired
	private HeadingDAO dao;
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		//System.out.println("redirect to welcome");
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Limit Login - XML");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;
	}

	@RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	public ModelAndView adminPage(ModelMap model) {
		try{
		model.put("heading", dao.list().get(0));
		}
		catch(Exception ex){
			Heading h = new Heading();
			h.setId(1);
			h.setMessage("Hi");
			dao.save(h);
			model.put("heading", dao.list().get(0));
		}
		
		return new ModelAndView("adminHomePage");

	}

	@RequestMapping(value = "/user/userHome", method = RequestMethod.GET)
	public ModelAndView userPage(ModelMap model) {
		try{
		model.put("heading", dao.list().get(0));
		}
		catch(Exception ex){
			Heading h = new Heading();
			h.setId(1);
			h.setMessage("Hi");
			dao.save(h);
			model.put("heading", dao.list().get(0));
		}
		
		return new ModelAndView("userHomePage");

	}

	
	@RequestMapping(value = "/admin/updateHeading",method = RequestMethod.POST)
	public ModelAndView updateHeading(ModelMap model,@ModelAttribute("heading") Heading heading) {
		try{
			
		dao.update(heading);
		model.put("msg", "Successfuly updated");
		}catch(Exception ex){
			model.put("error", "some error occured");
		}
		model.put("heading", dao.list().get(0));
		return new ModelAndView("adminHomePage");

	}

	/*@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}*/

	@RequestMapping(value = {"/login","pleaseLogin"})
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			//System.out.println("logincontroller"+getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			/*String targetUrl = getRememberMeTargetUrlFromSession(request);
			//System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
			}*/

			//model.addObject("error1", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			
			model.setViewName("login");
			return model;
		}

		if (logout != null) {
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("designationId");
			request.getSession().removeAttribute("departmentId");
			request.getSession().removeAttribute("departmentName");
			request.getSession().invalidate();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				model.addObject("msg",  userDetail.getUsername()+", You've been logged out successfully.");
			}
			else{
			model.addObject("msg", "You've been logged out successfully.");
			}
		}
		else{
			if (msg != null) {
				model.addObject("msg", msg);
			}
			if (error != null) {
				model.addObject("error", error);
			}
		}
		if (error != null) {
			model.addObject("error", error);
		}
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/user/logout")
	public ModelAndView userlogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			/*String targetUrl = getRememberMeTargetUrlFromSession(request);
			//System.out.println(targetUrl);
			if(StringUtils.hasText(targetUrl)){
				model.addObject("targetUrl", targetUrl);
			}*/
		}

		if (logout != null) {
			request.getSession().removeAttribute("userId");
			request.getSession().removeAttribute("designationId");
			request.getSession().removeAttribute("departmentId");
			request.getSession().removeAttribute("departmentName");
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetail = (UserDetails) auth.getPrincipal();
				model.addObject("msg",  userDetail.getUsername()+", You've been logged out successfully.");
			}
			else{
			model.addObject("msg", "You've been logged out successfully.");
			}
		}
		else{
			if (msg != null) {
				model.addObject("msg", msg);
			}
		}
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/logout",method=RequestMethod.POST)
	public ModelAndView logout() {
		return new ModelAndView("redirect:/login?logout=true");
	}
	
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
			////System.out.println("got message"+error);
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			//System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView notFound() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			//System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("404");
		return model;

	}

}