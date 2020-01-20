package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.NationalProgram;
//import com.models.Role;
import com.models.Program;

public interface NationalProgramDAO {
	void save(NationalProgram dept);
	void update(NationalProgram dept);
	void delete(NationalProgram dept);
	NationalProgram findByDeptId(Long deptId);
	List<NationalProgram> list();
	List<NationalProgram> list(Long start,Long end);
	
	Boolean IsExist(String NationalProgram);

	DataSet<NationalProgram> findNationalProgramWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<NationalProgram> getItemByModel(int page, int recordsPerPage);
	public List<NationalProgram> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<NationalProgram> getItemByCity(int page, int recordsPerPage,String city);
}
