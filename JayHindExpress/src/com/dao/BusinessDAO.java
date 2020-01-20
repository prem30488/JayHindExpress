package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Business;
//import com.models.Role;

public interface BusinessDAO {
	void save(Business dept);
	void update(Business dept);
	void delete(Business dept);
	Business findByDeptId(Long deptId);
	List<Business> list();
	List<Business> list(Long start,Long end);
	
	Boolean IsExist(String Business);

	DataSet<Business> findBusinessWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Business> getItemByModel(int page, int recordsPerPage);
	public List<Business> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Business> getItemByCity(int page, int recordsPerPage,String city);
	
}
