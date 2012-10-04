package com.intuit.coupongateway.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.intuit.coupongateway.vo.BaseVO;

public class BaseDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<? extends BaseVO> loadAll(String queryString){
		return loadAll(queryString,null);
	}
	
	public BaseVO load(String queryString,Object[] values){
		BaseVO baseVO=null;
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(queryString);
		if(values!=null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		baseVO=(BaseVO) query.uniqueResult();
		session.close();
		return baseVO;
	}
	
	public List<? extends BaseVO> loadAll(String queryString,Object[] values){
		List<? extends BaseVO> baseVOsList=null;
		Session session=sessionFactory.openSession();
		Query query=session.createQuery(queryString);
		if(values!=null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		baseVOsList=query.list();
		session.close();
		return baseVOsList;
	}
	
	public void save(List<? extends BaseVO> baseVOs) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		for (BaseVO baseVO : baseVOs) {
			session.save(baseVO);
		}
		transaction.commit();
		session.close();
	}
	
	public void save(BaseVO baseVO) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();		
		session.save(baseVO);
		session.flush();
		session.refresh(baseVO);
		transaction.commit();
		session.close();
	}
	
	public void saveOrUpdate(BaseVO baseVO) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();		
		session.saveOrUpdate(baseVO);
		session.flush();
		session.refresh(baseVO);
		transaction.commit();
		session.close();
	}
	
	public void update(BaseVO baseVO) {
		Session session=sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();		
		session.update(baseVO);
		session.flush();
		session.refresh(baseVO);
		transaction.commit();
		session.close();
	}
	
}
