package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Photography;
//import com.models.Role;

public interface PhotographyDAO {
	void save(Photography dept);
	void update(Photography dept);
	void delete(Photography dept);
	Photography findByDeptId(Long deptId);
	List<Photography> list();
	List<Photography> list(Long start,Long end);
	
	Boolean IsExist(String Photography);

	DataSet<Photography> findPhotographyWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Photography> getItemByModel(int page, int recordsPerPage);
	public List<Photography> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Photography> getItemByCity(int page, int recordsPerPage,String city);
	
}
