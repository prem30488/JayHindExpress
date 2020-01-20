package com.dao;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;

public interface RoleDAO {
	void save(Role role);
	void update(Role role);
	void delete(Role role);
	Role findByRoleId(Long roleId);
	List<Role> list();
	List<Role> list(int start,int end);
	DataSet<Role> findRolesWithDatatablesCriterias(DatatablesCriterias criterias);
	Boolean IsExist(String role);
}
