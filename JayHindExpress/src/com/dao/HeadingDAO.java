package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Heading;
//import com.models.Role;

public interface HeadingDAO {
	void save(Heading dept);
	void update(Heading dept);
	void delete(Heading dept);
	Heading findById(int id);
	List<Heading> list();
	List<Heading> list(int start,int end);
	
	Boolean IsExist(String program);

	DataSet<Heading> findHeadingWithDatatablesCriterias(DatatablesCriterias criterias);
}
