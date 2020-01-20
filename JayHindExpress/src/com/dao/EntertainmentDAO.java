package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Entertainment;
//import com.models.Role;

public interface EntertainmentDAO {
	void save(Entertainment dept);
	void update(Entertainment dept);
	void delete(Entertainment dept);
	Entertainment findByDeptId(Long deptId);
	List<Entertainment> list();
	List<Entertainment> list(Long start,Long end);
	
	Boolean IsExist(String Entertainment);

	DataSet<Entertainment> findEntertainmentWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Entertainment> getItemByModel(int page, int recordsPerPage);
	public List<Entertainment> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Entertainment> getItemByCity(int page, int recordsPerPage,String city);
}
