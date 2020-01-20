package com.controller.homepagesettings;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dao.HomePageDAO;
import com.models.HomePage;
import com.session.SessionManagement;

@Controller
public class HomePageSettingsController {

	@Autowired
	private HomePageDAO homepageDAO;
	
	@RequestMapping(value = "/admin/viewHomePageSettings", method = RequestMethod.GET)
	public ModelAndView viewSettings(@RequestParam(value = "error", required = false) String error,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		model.put("configuration",homepageDAO.getDefaultconfig());
		return new ModelAndView("viewHomePageSettingsAdminTile");
	}
	
	@RequestMapping(value = "/admin/changeHomePageSettings")
	public ModelAndView changeSettings(@Valid @Validated @ModelAttribute("configuration") HomePage configuration, BindingResult bindingResult,ModelMap model,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("viewSettingsAdminTile");
		}
		try{
			configuration.setId(1);
			homepageDAO.updateDefaultConfig(configuration);
			model.put("configuration",homepageDAO.getDefaultconfig());
			model.put("msg", "Settings changed successfully");
		}
		catch(Exception ex){
			ex.printStackTrace();
			model.put("error", ex.getMessage());
		}
		return new ModelAndView("viewHomePageSettingsAdminTile");
	}
	
}