package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Fashion;
//import com.models.Role;
import com.persistance.util.HibernateUtil;

public class FashionDAOImpl extends HibernateDaoSupport implements FashionDAO{

	public FashionDAOImpl(){
		setSessionFactory(HibernateUtil.getSessionFactory());
	}
	
	@Override
	@Transactional
	public void delete(Fashion dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(dept);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public Fashion findByDeptId(Long deptId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Fashion dept = (Fashion) session.get(Fashion.class, deptId);
		tx.commit();
		session.close();
		return dept;
	}
	@Override
	@Transactional
	public Boolean updateFrequency(Long deptId) {
		Fashion p = findByDeptId(deptId);
		p.setFrequency(p.getFrequency()+1);
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(p);
		tx.commit();
		session.close();
		return true;
	}
	@Override
	@Transactional
	public DataSet<Fashion> findFashionWithDatatablesCriterias(
			DatatablesCriterias criterias) {
		List<Fashion> Fashions = findFashionsCriterias(criterias);
		Long count = getTotalCount();
		Long countFiltered = getFilteredCount(criterias);

		return new DataSet<Fashion>(Fashions, count, countFiltered);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Fashion> list(){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Fashion");
		List<Fashion> list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Fashion> list(Long start,Long end){
		String hql="from Fashion";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setFirstResult(start.intValue());
		q.setMaxResults(end.intValue());
		List<Fashion> list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCount(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("SELECT d FROM Fashion d");
		queryBuilder.append(getFilterQuery(criterias));
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		/**
		*  paging
		*/
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<Fashion> list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		
		return Long.parseLong(String.valueOf(list.size()));
	}

	private Long getTotalCount() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(d) FROM Fashion d");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	private List<Fashion> findFashionsCriterias(
			DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("SELECT d from  Fashion d");

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
				orderParams.add("d." + columnDef.getName() + " " + columnDef.getSortDirection());
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

		List<Fashion> list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		
		return list;
	}

	private static StringBuilder getFilterQuery(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		
		/**
		 * Step 1.1: global filtering
		 */
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			queryBuilder.append(" WHERE ");

			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				
				
				
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if(columnDef.getName().equalsIgnoreCase("id")){
						if (criterias.getSearch().matches("[0-9]+")) {
						paramList.add(" d." + columnDef.getName()
								+ " = ?".replace("?", criterias.getSearch().toLowerCase()));
						}
					}else{
					paramList.add(" LOWER(d." + columnDef.getName()
							+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
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
						if(columnDef.getName().equalsIgnoreCase("id")){
							if (criterias.getSearch().matches("[0-9]+")) {
							paramList.add(" d." + columnDef.getName()
									+ " = ?".replace("?", criterias.getSearch().toLowerCase()));
							}
						}else{
						paramList.add(" LOWER(d." + columnDef.getName()
								+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
						}
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

	@Override
	@Transactional
	public void save(Fashion dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(dept);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public void update(Fashion dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(dept);
		tx.commit();
		session.close();
	}
	@Override
	public Boolean IsExist(String Fashion) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(d) FROM Fashion d where LOWER(Fashion)=LOWER('"+Fashion+"')");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		if(count>0)
		{
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fashion> getItemByModel(int page, int recordsPerPage) {
		List<Fashion> list = new ArrayList<Fashion>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM Fashion order by createdDate desc,frequency desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		//System.out.println(list.size());
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Fashion> getItemByFrequency(int page, int recordsPerPage) {
		List<Fashion> list = new ArrayList<Fashion>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM Fashion order by frequency desc, createdDate desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		//System.out.println("freq"+list.size());
		return list;
	}

	@Override
	public int getNoOfRecords() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM Fashion" );
		int l = q.list().size();
		tx.commit();
		session.close();
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fashion> getItemByCity(int page, int recordsPerPage, String city) {
		List<Fashion> list = new ArrayList<Fashion>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM Fashion where lower(location) like '%"+city.toLowerCase()+"%' order by createdDate desc,frequency desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<Fashion>) q.list();
		tx.commit();
		session.close();
		//System.out.println(list.size());
		return list;
	}
}
