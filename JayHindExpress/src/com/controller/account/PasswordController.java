package com.controller.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dao.ChangePasswordDAO;
import com.models.ChangePassword;
import com.session.SessionManagement;

@Controller
@RequestMapping(value={"/user/", "/admin/"})
public class PasswordController {
	
	@Autowired
	private ChangePasswordDAO changePasswordDAO;
		
	@RequestMapping(value = "change-password",method= RequestMethod.GET)
	public ModelAndView loadChangePasswordForm(ModelMap model) {
		model.put("changePassword",new ChangePassword());
		return new ModelAndView("changePasswordTile");	
	}
	
	@RequestMapping(value = "changePassword",method=RequestMethod.POST)
	public ModelAndView updatePasswordForm(ModelMap model, @ModelAttribute("changePassword") ChangePassword changePassword,BindingResult bindingResult,HttpServletRequest request) {
		if(SessionManagement.isSessionAlive()!=true)
		{
			return new ModelAndView("redirect:../pleaseLogin");
		}
		if(changePassword.getCurrentPassword().equals(null) || changePassword.getCurrentPassword().trim()==""){
			bindingResult.rejectValue("currentPassword", "error.currentPassword.required");
		}
		if(changePassword.getNewPassword().equals(null) || changePassword.getNewPassword().trim()==""){
			bindingResult.rejectValue("newPassword", "error.newPassword.required");
		}
		if(changePassword.getConfirmPassword()==null || changePassword.getConfirmPassword().trim()==""){
			bindingResult.rejectValue("confirmPassword", "error.confirmPassword.required");
		}
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("changePasswordTile");
		}
		if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword()))
		{
			bindingResult.rejectValue("confirmPassword", "error.password.mismatch");
		}
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("changePasswordTile");
		}
		
		if(changePasswordDAO.changePassword(Integer.valueOf(SessionManagement.getUserId()), changePassword)==false){
			bindingResult.rejectValue("currentPassword", "error.password.incorrect");
		}
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("changePasswordTile");
		}
		model.put("msg", "Your Password has been changed successfully");
		return new ModelAndView("changePasswordTile");
	}
}