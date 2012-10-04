package com.intuit.coupongateway.service;

import java.util.Map;

import javax.ws.rs.FormParam;

import com.intuit.coupongateway.vo.Merchant;
import com.intuit.coupongateway.vo.MerchantCoupon;

public interface IMerchantService {

	public Merchant authenticateMerchant(@FormParam("") Merchant merchant);
	
	public Map<String, Object> getCouponList(@FormParam("")MerchantCoupon merchantCoupon);
	
}
