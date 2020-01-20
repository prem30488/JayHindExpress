package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Advertisement;
//import com.models.Role;

public interface AdvertisementDAO {
	void save(Advertisement dept);
	void update(Advertisement dept);
	void delete(Advertisement dept);
	Advertisement findByDeptId(Long deptId);
	List<Advertisement> list();
	List<Advertisement> list(Long start,Long end);
	
	Boolean IsExist(String Advertisement);

	DataSet<Advertisement> findAdvertisementWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Advertisement> getItemByModel(int page, int recordsPerPage);
	public List<Advertisement> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Advertisement> getItemByCity(int page, int recordsPerPage,String city);
	
	public List<Advertisement> getItemByActiveModel(int page, int recordsPerPage);
}
