package com.uploader.dao;

import java.util.List;

import com.uploader.entity.EpaperRequest;

public interface EpaperRequestDAO {
	public List<EpaperRequest> getEpaperRequests();

	public void addEpaperRequest(EpaperRequest request);
}
