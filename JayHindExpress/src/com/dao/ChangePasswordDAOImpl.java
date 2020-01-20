package com.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.models.ChangePassword;
import com.models.UserLogin;

public class ChangePasswordDAOImpl implements ChangePasswordDAO {
	
	@Autowired
	private UserLoginDAO userLoginDAO;
	
	@Override
	public Boolean changePassword(int id,ChangePassword changePassword) {
		UserLogin user = userLoginDAO.findByUserId(id);
		if(user.getPassword().equals(changePassword.getCurrentPassword())){
			user.setPassword(changePassword.getNewPassword());
			userLoginDAO.update(user);
			return true;
		}
		return false;
	}

}