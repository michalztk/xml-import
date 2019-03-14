package com.uploader.service;

import java.util.List;
import com.uploader.entity.EpaperRequest;

public interface EpaperRequestService {
	public List<EpaperRequest> getEpaperRequests();

	public void addEpaperRequest(EpaperRequest request);
}
