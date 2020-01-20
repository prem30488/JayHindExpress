package com.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.UserLogin;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class UserLoginDAOImpl extends HibernateDaoSupport implements UserLoginDAO{
	
	public UserLoginDAOImpl(){
		setSessionFactory(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public void save(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(login);
		tx.commit();
		session.close();
	}

	@Override
	public void update(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(login);
		tx.commit();
		session.close();
	}
	
	@Override
	public void delete(UserLogin login) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(login);
		tx.commit();
		session.close();
	}

	@Override
	public UserLogin findByRoleId(int userId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		UserLogin user = (UserLogin)session.get(UserLogin.class, userId);
		tx.commit();
		session.close();
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserLogin> list() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		session.setFlushMode(FlushMode.ALWAYS);
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from UserLogin");
		List<UserLogin> list = (List<UserLogin>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserLogin> list(int start, int end) {
		String hql="from UserLogin";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setFirstResult(start);
		q.setMaxResults(end);
		List<UserLogin> list = (List<UserLogin>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean authenticate(String userName, String password)
	{
		String hql="FROM UserLogin U where U.userName='"+userName+"' and U.password = '"+password+"' and U.enabled=1";
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			users = (List<UserLogin>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return false;
		} 
		if(users.size()>0)
		{
			/*Log log = new Log();
			log.setActivityId(Long.valueOf(1));
			log.setInformation(username + " logged in");
			log.setActivityBy(users.get(0).getId());
			logdao.addLog(log);*/
			return true;
		}
		else
		{
			return false;
		}		
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserLogin findUser(String userName, String password)
	{
		String hql="FROM UserLogin U where U.userName='"+userName+"' and U.password = '"+password+"' and U.enabled=1";
		List<UserLogin> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query= session.createQuery(hql);
			users = (List<UserLogin>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} 
		if(users.size()>0)
		{
			return users.get(0);
		}
		else
		{
			return null;
		}		
	}
	@Override
	@SuppressWarnings("unchecked")
	public UserLogin findUser(String userName)
	{
		String hql="FROM UserLogin where userName='"+userName+"'";
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		List<UserLogin> list = (List<UserLogin>) q.list();
		tx.commit();
		session.close();
		if(list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}		
	}

	@Override
	public DataSet<UserLogin> findUsersWithDatatablesCriterias(
			DatatablesCriterias criterias) {
		List<UserLogin> users = findUsersCriterias(criterias);
		Long count = getTotalCount();
		Long countFiltered = getFilteredCount(criterias);

		return new DataSet<UserLogin>(users, count, countFiltered);
	}
	
	

	@SuppressWarnings("unchecked")
	private List<UserLogin> findUsersCriterias(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("From UserLogin u");

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
				orderParams.add("u." + columnDef.getName() + " " + columnDef.getSortDirection());
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

		List<UserLogin> list = (List<UserLogin>) q.list();
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
					paramList.add(" LOWER(u." + columnDef.getName()
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
						paramList.add(" LOWER(u." + columnDef.getName()
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
	private Long getFilteredCount(DatatablesCriterias criterias) {
		StringBuilder queryBuilder = new StringBuilder("From UserLogin u");
		queryBuilder.append(getFilterQuery(criterias));
		
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		
		/**
		*  paging
		*/
		q.setFirstResult(criterias.getDisplayStart());
		q.setMaxResults(criterias.getDisplaySize());

		List<UserLogin> list = (List<UserLogin>) q.list();
		tx.commit();
		session.close();
		
		return Long.parseLong(String.valueOf(list.size()));
	}
	
	private Long getTotalCount() {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT COUNT(u) FROM UserLogin u");
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}

	@Override
	public UserLogin getUser(String name) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("FROM UserLogin u where u.userName='"+name+"'");
		UserLogin user = (UserLogin) q.list().get(0);
		tx.commit();
		session.close();
		return user;
	}

	@Override
	public UserLogin findByUserId(int userId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		UserLogin user = (UserLogin)session.get(UserLogin.class, userId);
		tx.commit();
		session.close();
		return user;
	}
}
