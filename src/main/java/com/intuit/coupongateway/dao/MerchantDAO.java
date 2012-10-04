package com.intuit.coupongateway.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.intuit.coupongateway.service.MerchantService;
import com.intuit.coupongateway.vo.Coupon;
import com.intuit.coupongateway.vo.Merchant;
import com.intuit.coupongateway.vo.MerchantCoupon;
import com.intuit.coupongateway.vo.Status;

public class MerchantDAO extends BaseDAO{

	private Logger logger=Logger.getLogger(MerchantService.class);
	
	public List<MerchantCoupon> loadAllCoupons(String queryString,Object[] values) {
		List<MerchantCoupon> baseVOsList=new ArrayList<MerchantCoupon>();
		Session session=getSessionFactory().openSession();
		Query query=session.createSQLQuery(queryString);
		logger.info("Query Result: "+query);
		if(values!=null){
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		List<Object[]> objects=query.list();
		MerchantCoupon merchantCoupon=new MerchantCoupon();
		
		for (Object[] object : objects) {
			
			merchantCoupon=new MerchantCoupon();
			
			Merchant merchant=new Merchant();
			merchant.setMerchantID((String) object[0]);
			merchantCoupon.setMerchant(merchant);
			
			Coupon coupon=new Coupon();
			coupon.setCouponID((String) object[1]);
			if(object[5]!=null){
				coupon.setCouponValue((Integer) object[5]);
			}
			merchantCoupon.setCoupon(coupon);
			
			Status status=new Status();
			status.setStatusName((String) object[2]);
			merchantCoupon.setStatus(status);
			
			merchantCoupon.setTransDate((Date) object[3]);
			if(object[4] instanceof Integer){
				merchantCoupon.setOrderNo( new Long((Integer) object[4]));
			}else if(object[4] instanceof Long){
				merchantCoupon.setOrderNo( (Long) object[4]);
			}
			
			
			baseVOsList.add(merchantCoupon);
		}
		logger.info("Query Result: "+baseVOsList);
		System.out.println("Query Result: "+baseVOsList);
		session.close();		
		return baseVOsList;		
		
	}
	
}
