package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Link;
//import com.models.Role;

public interface LinkDAO {
	void save(Link dept);
	void update(Link dept);
	void delete(Link dept);
	Link findByDeptId(Long deptId);
	List<Link> list();
	List<Link> list(Long start,Long end);
	
	Boolean IsExist(String Link);

	DataSet<Link> findLinkWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Link> getItemByModel(int page, int recordsPerPage);
	public List<Link> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Link> getItemByCity(int page, int recordsPerPage,String city);
	
}
