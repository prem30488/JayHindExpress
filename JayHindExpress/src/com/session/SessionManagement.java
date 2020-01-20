package com.session;

import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManagement {
	
	public static HttpSession getSession()
	{
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		if(attribute.getRequest().getSession(true)!=null){
		return attribute.getRequest().getSession(true);
		}
		else{
			System.out.println("session null");
			return null;
		}
	}
	
	public static String getUserId(){
		if(getSession().getAttribute("userId")==null){
			return null;
		}
		return getSession().getAttribute("userId").toString();
	}
	
	public static Boolean isSessionAlive()
	{
		if(getUserId() == null)
		{
			return false;
		}
		return true;
	}
	
}
