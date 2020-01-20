package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Poll;
//import com.models.Role;

public interface PollDAO {
	void save(Poll dept);
	void update(Poll dept);
	void delete(Poll dept);
	Poll findByDeptId(Long deptId);
	List<Poll> list();
	List<Poll> list(Long start,Long end);
	
	Boolean IsExist(String Poll);

	DataSet<Poll> findPollWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Poll> getItemByModel(int page, int recordsPerPage);
	public List<Poll> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
}
