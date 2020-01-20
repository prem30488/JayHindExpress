package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Technology;
//import com.models.Role;

public interface TechnologyDAO {
	void save(Technology dept);
	void update(Technology dept);
	void delete(Technology dept);
	Technology findByDeptId(Long deptId);
	List<Technology> list();
	List<Technology> list(Long start,Long end);
	
	Boolean IsExist(String Technology);

	DataSet<Technology> findTechnologyWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Technology> getItemByModel(int page, int recordsPerPage);
	public List<Technology> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Technology> getItemByCity(int page, int recordsPerPage,String city);
}
