package com.uploader.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uploader.dao.EpaperRequestDAO;
import com.uploader.entity.EpaperRequest;

/**
 * Service to separate business logic from DAO. Transactional annotation was
 * placed in service layer for each method because there will be no need to
 * repeat it for each potential DAO (e.g. for other database DAO)
 * 
 * @author michal
 *
 */
@Service
public class EpaperRequestServiceImpl implements EpaperRequestService {

	@Autowired
	private EpaperRequestDAO ePaperRequestDAO; // DAO object needs to be injected

	/**
	 * Reads epaper requests list
	 */
	@Override
	@Transactional
	public List<EpaperRequest> getEpaperRequests() {
		return ePaperRequestDAO.getEpaperRequests();
	}

	/**
	 * Saves epaper request
	 */
	@Override
	@Transactional
	public void addEpaperRequest(EpaperRequest request) {
		ePaperRequestDAO.addEpaperRequest(request);

	}

}
