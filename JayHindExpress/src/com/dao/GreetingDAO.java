package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Greeting;
//import com.models.Role;

public interface GreetingDAO {
	void save(Greeting dept);
	void update(Greeting dept);
	void delete(Greeting dept);
	Greeting findByDeptId(Long deptId);
	List<Greeting> list();
	List<Greeting> list(Long start,Long end);
	
	Boolean IsExist(String Greeting);

	DataSet<Greeting> findGreetingWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Greeting> getItemByModel(int page, int recordsPerPage);
	public List<Greeting> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Greeting> getItemByCity(int page, int recordsPerPage,String city);
	
	public List<Greeting> getItemByActiveModel(int page, int recordsPerPage);
}
