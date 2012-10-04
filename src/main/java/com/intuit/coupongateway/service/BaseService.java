package com.intuit.coupongateway.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.intuit.coupongateway.vo.BaseVO;

public class BaseService {

	private Logger logger = Logger.getLogger(BaseService.class);

	

	public Map<String, Object> populateSearchResultsMap(
			List<? extends BaseVO> baseVOs) {
		
		String pageParam = null;		
		
		Map<String, Object> searchResultsMap = new HashMap<String, Object>();
		long size = baseVOs.size();
		long page = 0;
		long total = 0;
		int pageRowCount = 10;
		if (size != 0) {
			long tempRem = (size % pageRowCount);
			long tempTotal = (size / pageRowCount);
			total = ((tempRem == 0) ? (tempTotal) : (tempTotal + 1));
			page=(pageParam==null?1:Integer.parseInt(pageParam));
		} else {
			page = 0;
			total = 0;
		}
		searchResultsMap.put("page", page);
		searchResultsMap.put("rows", baseVOs);
		searchResultsMap.put("records", size);
		searchResultsMap.put("total", total);
		logger.info("[populateSearchResultsMap] Method End");
		return searchResultsMap;
	}
}
