package com.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dao.UserAttemptsDAO;
import com.dao.UserAttemptsDAOImpl;
import com.models.UserAttempts;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
/**
* @author Parth Trivedi
* @version 1.0
*/
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	private UserAttemptsDAO userAttemptsDAO;
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public LimitLoginAuthenticationProvider(){
		userAttemptsDAO=new UserAttemptsDAOImpl();
	}
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		
		//System.err.println(attr.getRequest().getParameter("myParameterName"));
		//validate captcha here
		
		String remoteAddr = attr.getRequest().getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6Ld0HBoUAAAAAArZ0ydekDD7n6WBs7IFPtXKgCvJ");

        String challenge = attr.getRequest().getParameter("recaptcha_challenge_field");
        String uresponse = attr.getRequest().getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

        if (reCaptchaResponse.isValid()) {
          //out.print("Answer was entered correctly!");
        	if(authenticate(attr.getRequest().getParameter("username"),attr.getRequest().getParameter("password"))==false){
        		String error = "";
    				error = "password is incorrect";
    			throw new LockedException(error);
        	}

		
		try {
			
			Authentication auth = super.authenticate(authentication);
			userAttemptsDAO.resetFailAttempts(authentication.getName());
			//System.out.println("here");
			return auth;

		} catch (BadCredentialsException e) {
			//System.out.println("password is incorrect");
			//userAttemptsDAO.updateFailAttempts(authentication.getName());
			throw e;
		} catch (LockedException e) {
			String error = "";
			UserAttempts userAttempts = userAttemptsDAO.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}
			throw new LockedException(error);
		}
        } else {
            //out.print("Answer is wrong");
        	//s response.sendRedirect("../login?error=Invalid captcha");
            	String error = "Invalid Captcha";
            	//System.out.println("limit"+error);
            	throw new LockedException(error);
          }
	}
	public UserAttemptsDAO getUserAttemptsDAO() {
		return userAttemptsDAO;
	}
	public void setUserAttemptsDAO(UserAttemptsDAO userAttemptsDAO) {
		this.userAttemptsDAO = userAttemptsDAO;
	}
	
	// returns AuthenticatedUser object if authentication is successful, otherwise null
	public Boolean authenticate(String username, String password) {   
	    Boolean flag=false;
		Connection connection = null;
		 try {
		        connection = dataSource.getConnection();
		  
		PreparedStatement st =  (PreparedStatement) connection.prepareStatement("select user_name as username,password,enabled,accountNonExpired,accountNonLocked,credentialsNonExpired from logininformation where user_name = ? and password = ?");
	    st.setString(1, username);
	    st.setString(2, password);

	    ResultSet rs = (ResultSet) st.executeQuery();

	    //login valid because there is something from the result set, then create user object
	    if (rs.next() ) {
	        // set all the useful user information in this POJO
	    	flag=true;
	    }

	     // close resultset, preparedStatement, connection, clean up, etc.

	    rs.close();
	    connection.close();
		 }catch(Exception e){
			 e.printStackTrace();			 
		 }
		
	    return flag;  
	}
}