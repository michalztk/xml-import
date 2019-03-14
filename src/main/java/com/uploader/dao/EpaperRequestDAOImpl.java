package com.uploader.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uploader.entity.EpaperRequest;

/**
 * DAO class
 * 
 * @author michal
 *
 */
@Repository
public class EpaperRequestDAOImpl implements EpaperRequestDAO {

	@Autowired
	private SessionFactory sessionFactory; // injects hibernate session factory

	/**
	 * Reads epaper requests from database
	 */
	@Override
	public List<EpaperRequest> getEpaperRequests() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create query to list epaper requests ordered by upload time
		Query<EpaperRequest> query = currentSession.createQuery("from EpaperRequest order by uploadTime desc ",
				EpaperRequest.class);
		// execute query and get the result list
		List<EpaperRequest> result = query.getResultList();
		return result;
	}

	/**
	 * Saves epaper requests to database
	 */
	@Override
	public void addEpaperRequest(EpaperRequest request) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// save epaper request to database
		currentSession.save(request);
	}

}
