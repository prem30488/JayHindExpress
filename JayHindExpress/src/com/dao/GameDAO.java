package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Game;
//import com.models.Role;

public interface GameDAO {
	void save(Game dept);
	void update(Game dept);
	void delete(Game dept);
	Game findByDeptId(Long deptId);
	List<Game> list();
	List<Game> list(Long start,Long end);
	
	Boolean IsExist(String Game);

	DataSet<Game> findGameWithDatatablesCriterias(DatatablesCriterias criterias);
	
	public List<Game> getItemByModel(int page, int recordsPerPage);
	public List<Game> getItemByFrequency(int page, int recordsPerPage);
	public int getNoOfRecords();
	public Boolean updateFrequency(Long deptId);
	
	public List<Game> getItemByCity(int page, int recordsPerPage,String city);
}
