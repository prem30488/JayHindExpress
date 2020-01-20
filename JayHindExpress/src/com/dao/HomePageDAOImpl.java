package com.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.HomePage;
import com.persistance.util.HibernateUtil;

public class HomePageDAOImpl implements HomePageDAO {
	
	@Override
	public HomePage getconfig(int configId) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		HomePage config = (HomePage) session.get(HomePage.class, configId);
		tx.commit();
		session.close();
		return config;
	}
	
	@Override
	public HomePage getDefaultconfig() {
		return getconfig(1);
	}
	
	@Override
	public void updateDefaultConfig(HomePage defaultConfig) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(defaultConfig);
		}catch(Exception e){
			tx.rollback();
		}
		tx.commit();
		session.close();
	}
}
