package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.User;
//import com.models.Role;

public interface UserDAO {
	void save(User dept);
	void update(User dept);
	void delete(User dept);
	User findByDeptId(Long deptId);
	List<User> list();
	List<User> list(Long start,Long end);
	
	Boolean IsExist(String program);

	DataSet<User> findUserWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<User> getItemByModel(int page, int recordsPerPage);
	public List<User> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<User> getItemByCityFrequency(int page, int recordsPerPage,String city);
	public List<User> getItemByCity(int page, int recordsPerPage,String city);
	
}
