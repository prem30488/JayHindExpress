package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Sponsor;
//import com.models.Role;

public interface SponsorDAO {
	void save(Sponsor dept);
	void update(Sponsor dept);
	void delete(Sponsor dept);
	Sponsor findByDeptId(Long deptId);
	List<Sponsor> list();
	List<Sponsor> list(Long start,Long end);
	
	Boolean IsExist(String Sponsor);

	DataSet<Sponsor> findSponsorWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Sponsor> getItemByModel(int page, int recordsPerPage);
	public List<Sponsor> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Sponsor> getItemByCity(int page, int recordsPerPage,String city);
	
	public List<Sponsor> getItemByActiveModel(int page, int recordsPerPage);
}
