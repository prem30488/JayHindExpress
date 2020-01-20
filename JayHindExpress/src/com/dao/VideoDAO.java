package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Program;
import com.models.Video;
//import com.models.Role;

public interface VideoDAO {
	void save(Video video);
	void update(Video video);
	void delete(Video video);
	Video findByDeptId(Long videoId);
	List<Video> list();
	List<Video> list(int start,int end);
	
	Boolean IsExist(String video);

	DataSet<Video> findVideoWithDatatablesCriterias(DatatablesCriterias criterias);
	

	public List<Video> getItemByModel(int page, int recordsPerPage);
	public List<Video> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Video> getItemByCity(int page, int recordsPerPage,String city);
}
