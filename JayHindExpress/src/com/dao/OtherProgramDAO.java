package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.OtherProgram;
//import com.models.Role;
import com.models.Program;

public interface OtherProgramDAO {
	void save(OtherProgram dept);
	void update(OtherProgram dept);
	void delete(OtherProgram dept);
	OtherProgram findByDeptId(Long deptId);
	List<OtherProgram> list();
	List<OtherProgram> list(Long start,Long end);
	
	Boolean IsExist(String program);

	DataSet<OtherProgram> findOtherProgramWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<OtherProgram> getItemByModel(int page, int recordsPerPage);
	public List<OtherProgram> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<OtherProgram> getItemByCity(int page, int recordsPerPage,String city);
}
