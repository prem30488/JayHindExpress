package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Contact;
//import com.models.Role;

public interface ContactDAO {
	void save(Contact dept);
	void update(Contact dept);
	void delete(Contact dept);
	Contact findById(int id);
	List<Contact> list();
	List<Contact> list(int start,int end);
	
	Boolean IsExist(String program);

	DataSet<Contact> findContactWithDatatablesCriterias(DatatablesCriterias criterias);
}
