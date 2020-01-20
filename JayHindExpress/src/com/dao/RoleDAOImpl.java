package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;
import com.persistance.util.HibernateUtil;

@Service
public class RoleDAOImpl extends HibernateDaoSupport implements RoleDAO{
	
	public RoleDAOImpl(){
		setSessionFactory(HibernateUtil.getSessionFactory());
	}
	
	@Override
	@Transactional
	public void save(Role role) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(role);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public void update(Role role) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(role);
		tx.commit();
		session.close();
	}
	
	@Override
	@Transactional
	public void delete(Role role) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(role);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public Role findByRoleId(Long roleId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Role role = (Role) session.get(Role.class, roleId);
		tx.commit();
		session.close();
		return role;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> list(){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Role");
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Role> list(int start,int end){
		String hql="from Role";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setFirstResult(start);
		q.setMaxResults(end);
		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public DataSet<Role> findRolesWithDatatablesCriterias(
			DatatablesCriterias criterias) {
		List<Role> roles = findRolesCriterias(criterias);
		Long count = getTotalCount();
		Long countFiltered = getFilteredCount(criterias);

		return new DataSet<Role>(roles, count, countFiltered);

	}

	@SuppressWarnings("unchecked")
	public List<Role> findRolesCriterias(DatatablesCriterias criterias) {

		StringBuilder queryBuilder = new StringBuilder("SELECT r from  Role r");

		/**
		 * Step 1: global and individual column filtering
		 */
		queryBuilder.append(getFilterQuery(criterias));

		/**
		 * Step 2: sorting
		 */
		if (criterias.hasOneSortedColumn()) {

			List<String> orderParams = new ArrayList<String>();
			queryBuilder.append(" ORDER BY ");
			for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
				orderParams.add("r." + columnDef.getName() + " " + columnDef.getSortDirection());
			}

			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		/**
		 * Step 3: paging
		 */
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		
		return list;
	}
	
	public static StringBuilder getFilterQuery(DatatablesCriterias criterias){
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		
		/**
		 * Step 1.1: global filtering
		 */
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			queryBuilder.append(" WHERE ");

			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					paramList.add(" LOWER(r." + columnDef.getName()
							+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
				}
			}

			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
		}

		/**
		 * Step 1.2: individual column filtering
		 */
		if (criterias.hasOneFilterableColumn() && criterias.hasOneFilteredColumn()) {
			paramList = new ArrayList<String>();
			
			if(!queryBuilder.toString().contains("WHERE")){
				queryBuilder.append(" WHERE ");
			}
			else{
				queryBuilder.append(" AND ");
			}

			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable()){
					if(StringUtils.isNotBlank(columnDef.getSearch())) {
						paramList.add(" LOWER(r." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", columnDef.getSearch().toLowerCase()));
					}
				}
			}

			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" AND ");
				}
			}
		}
		
		return queryBuilder;
	}
	
	@SuppressWarnings("unchecked")
	public Long getFilteredCount(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("SELECT r FROM Role r");
		queryBuilder.append(getFilterQuery(criterias));
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		/**
		*  paging
		*/
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<Role> list = (List<Role>) q.list();
		tx.commit();
		session.close();
		
		return Long.parseLong(String.valueOf(list.size()));
	}

	/**
	* @return the total count of roles.
	*/
	public Long getTotalCount() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(r) FROM Role r");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@Override
	public Boolean IsExist(String role) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(r) FROM Role r where LOWER(role)=LOWER('"+role+"')");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		if(count>0)
		{
			return true;
		}
		return false;
	}

}
