package com.persistance.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
/**
* @author Parth Trivedi
* @version 1.0
*/
public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	 private static ServiceRegistry serviceRegistry;
	 
	 static
	 {
		 try
	     {
			 Configuration configuration = new Configuration().configure();

			 serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.configure("hibernate.cfg.xml").getProperties()).buildServiceRegistry();
			 sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     }
		 catch (HibernateException Ex)
		 {
			 throw new ExceptionInInitializerError(Ex);
		 }
	 }
	 public static SessionFactory getSessionFactory()
	 {
		 return sessionFactory;
	 }
}