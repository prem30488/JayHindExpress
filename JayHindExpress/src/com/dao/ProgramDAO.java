package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Program;
//import com.models.Role;

public interface ProgramDAO {
	void save(Program dept);
	void update(Program dept);
	void delete(Program dept);
	Program findByDeptId(Long deptId);
	List<Program> list();
	List<Program> list(Long start,Long end);
	
	Boolean IsExist(String program);

	DataSet<Program> findProgramWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Program> getItemByModel(int page, int recordsPerPage);
	public List<Program> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Program> getItemByCityFrequency(int page, int recordsPerPage,String city);
	public List<Program> getItemByCity(int page, int recordsPerPage,String city);
	
}
