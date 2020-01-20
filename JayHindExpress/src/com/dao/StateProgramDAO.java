package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Program;
import com.models.StateProgram;
//import com.models.Role;

public interface StateProgramDAO {
	void save(StateProgram dept);
	void update(StateProgram dept);
	void delete(StateProgram dept);
	StateProgram findByDeptId(Long deptId);
	List<StateProgram> list();
	List<StateProgram> list(Long start,Long end);
	
	Boolean IsExist(String program);

	DataSet<StateProgram> findStateProgramWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<StateProgram> getItemByModel(int page, int recordsPerPage);
	public List<StateProgram> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<StateProgram> getItemByCity(int page, int recordsPerPage,String city);
}
