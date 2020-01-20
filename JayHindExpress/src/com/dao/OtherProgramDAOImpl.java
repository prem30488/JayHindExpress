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
import com.models.OtherProgram;
import com.models.Program;
//import com.models.Role;
import com.persistance.util.HibernateUtil;

public class OtherProgramDAOImpl extends HibernateDaoSupport implements OtherProgramDAO{

	public OtherProgramDAOImpl(){
		setSessionFactory(HibernateUtil.getSessionFactory());
	}
	
	@Override
	@Transactional
	public void delete(OtherProgram dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(dept);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public OtherProgram findByDeptId(Long deptId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		OtherProgram dept = (OtherProgram) session.get(OtherProgram.class, deptId);
		tx.commit();
		session.close();
		return dept;
	}

	@Override
	@Transactional
	public Boolean updateFrequency(Long deptId) {
		OtherProgram p = findByDeptId(deptId);
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
	public DataSet<OtherProgram> findOtherProgramWithDatatablesCriterias(
			DatatablesCriterias criterias) {
		List<OtherProgram> programs = findOtherProgramsCriterias(criterias);
		Long count = getTotalCount();
		Long countFiltered = getFilteredCount(criterias);

		return new DataSet<OtherProgram>(programs, count, countFiltered);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<OtherProgram> list(){
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from OtherProgram");
		List<OtherProgram> list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<OtherProgram> list(Long start,Long end){
		String hql="from OtherProgram";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setFirstResult(start.intValue());
		q.setMaxResults(end.intValue());
		List<OtherProgram> list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	private Long getFilteredCount(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("SELECT d FROM OtherProgram d");
		queryBuilder.append(getFilterQuery(criterias));
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		/**
		*  paging
		*/
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<OtherProgram> list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		
		return Long.parseLong(String.valueOf(list.size()));
	}

	private Long getTotalCount() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(d) FROM OtherProgram d");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	
	@SuppressWarnings("unchecked")
	private List<OtherProgram> findOtherProgramsCriterias(
			DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("SELECT d from  OtherProgram d");

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

		List<OtherProgram> list = (List<OtherProgram>) q.list();
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
	public void save(OtherProgram dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(dept);
		tx.commit();
		session.close();
	}

	@Override
	@Transactional
	public void update(OtherProgram dept) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(dept);
		tx.commit();
		session.close();
	}
	@Override
	public Boolean IsExist(String program) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(d) FROM OtherProgram d where LOWER(program)=LOWER('"+program+"')");
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
	public List<OtherProgram> getItemByModel(int page, int recordsPerPage) {
		List<OtherProgram> list = new ArrayList<OtherProgram>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM OtherProgram order by createdDate desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		//System.out.println(list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OtherProgram> getItemByFrequency(int page, int recordsPerPage) {
		List<OtherProgram> list = new ArrayList<OtherProgram>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM OtherProgram order by frequency desc, createdDate desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		//System.out.println("freq"+list.size());
		return list;
	}
	
	@Override
	public int getNoOfRecords() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM OtherProgram" );
		int l = q.list().size();
		tx.commit();
		session.close();
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OtherProgram> getItemByCity(int page, int recordsPerPage, String city) {
		
		List<OtherProgram> list = new ArrayList<OtherProgram>();
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM OtherProgram where lower(location) like '%"+city.toLowerCase()+"%' order by createdDate desc" );
		q.setFirstResult(page); 
        q.setMaxResults(recordsPerPage);
		list = (List<OtherProgram>) q.list();
		tx.commit();
		session.close();
		//System.out.println(list.size());
		return list;
	}
}
