package com.intuit.coupongateway.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.QueryParam;

import com.intuit.coupongateway.vo.MerchantCoupon;

public interface ICouponPayment {

	MerchantCoupon validateFormParamAndCharge(
			@FormParam("") MerchantCoupon merchantCoupon);
	
	MerchantCoupon validateMatrixParamAndCharge(
			@MatrixParam("") MerchantCoupon merchantCoupon);
	
	MerchantCoupon validateQueryParamAndCharge(
			@QueryParam("") MerchantCoupon merchantCoupon);
}
