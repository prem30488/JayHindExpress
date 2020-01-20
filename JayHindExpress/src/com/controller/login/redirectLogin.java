package com.controller.login;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.dao.UserDAO;
import com.dao.UserLoginDAO;
import com.models.User;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

public class redirectLogin extends SavedRequestAwareAuthenticationSuccessHandler{

	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		int userId = userLoginDAO.getUser(authentication.getName()).getUserId();
		//System.out.println("userid:"+userId);
		request.getSession().setAttribute("userId", userId);
		
		User user = userDAO.findByDeptId((long) userId);
		 request.getSession().setAttribute("user", user);
		
		 if (roles.contains("admin")) {
			 //System.out.println("redirecting to admin home page");
	            response.sendRedirect("../admin/adminHome");
	        }
		 else if (roles.contains("user")){
			 
			 //request.getSession().setAttribute("national", user.getNational());
			 //request.getSession().setAttribute("state", user.getState());
			 //request.getSession().setAttribute("international", user.getInternational());
			 //System.out.println("redirecting to user home page");
            response.sendRedirect("../user/userHome");
		 }
		 else {
			 //System.out.println("redirecting to user home page");
            response.sendRedirect("../404");
        }
	}

	
}
