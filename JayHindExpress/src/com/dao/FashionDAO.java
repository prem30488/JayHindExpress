package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Fashion;
//import com.models.Role;

public interface FashionDAO {
	void save(Fashion dept);
	void update(Fashion dept);
	void delete(Fashion dept);
	Fashion findByDeptId(Long deptId);
	List<Fashion> list();
	List<Fashion> list(Long start,Long end);
	
	Boolean IsExist(String Fashion);

	DataSet<Fashion> findFashionWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Fashion> getItemByModel(int page, int recordsPerPage);
	public List<Fashion> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Fashion> getItemByCity(int page, int recordsPerPage,String city);
}
