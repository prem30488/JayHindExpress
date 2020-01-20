package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.UserLogin;

public interface UserLoginDAO {
	void save(UserLogin login);
	void update(UserLogin login);
	void delete(UserLogin login);
	UserLogin findByRoleId(int userId);
	List<UserLogin> list();
	List<UserLogin> list(int start,int end);
	Boolean authenticate(String userName, String password);
	UserLogin findUser(String userName, String password);
	UserLogin findUser(String userName);
	DataSet<UserLogin> findUsersWithDatatablesCriterias(
			DatatablesCriterias criterias);
	UserLogin getUser(String name);
	public UserLogin findByUserId(int userId);
}
