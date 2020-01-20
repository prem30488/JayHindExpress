package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Sport;
//import com.models.Role;

public interface SportDAO {
	void save(Sport dept);
	void update(Sport dept);
	void delete(Sport dept);
	Sport findByDeptId(Long deptId);
	List<Sport> list();
	List<Sport> list(Long start,Long end);
	
	Boolean IsExist(String Sport);

	DataSet<Sport> findSportWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Sport> getItemByModel(int page, int recordsPerPage);
	public List<Sport> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);

	public List<Sport> getItemByCity(int page, int recordsPerPage,String city);
}
