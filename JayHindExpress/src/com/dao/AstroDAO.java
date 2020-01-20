package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Astro;
//import com.models.Role;

public interface AstroDAO {
	void save(Astro dept);
	void update(Astro dept);
	void delete(Astro dept);
	Astro findByDeptId(Long deptId);
	List<Astro> list();
	List<Astro> list(Long start,Long end);
	
	Boolean IsExist(String Astro);

	DataSet<Astro> findAstroWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Astro> getItemByModel(int page, int recordsPerPage);
	public List<Astro> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Astro> getItemByCity(int page, int recordsPerPage,String city);
	
}
