package com.intuit.coupongateway.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.log4j.Logger;

import com.intuit.coupongateway.constant.CouponConstants;
import com.intuit.coupongateway.dao.CouponPaymentDAO;
import com.intuit.coupongateway.util.DateUtil;
import com.intuit.coupongateway.vo.Coupon;
import com.intuit.coupongateway.vo.Merchant;
import com.intuit.coupongateway.vo.MerchantCoupon;
import com.intuit.coupongateway.vo.Status;

@Path("/couponPay/")
public class CouponPayment implements ICouponPayment {

	private Logger logger = Logger.getLogger(CouponPayment.class);

	private CouponPaymentDAO couponPaymentDAO;	
	
	@Context
	private MessageContext messageContext;

	public CouponPaymentDAO getCouponPaymentDAO() {
		return couponPaymentDAO;
	}

	public void setCouponPaymentDAO(CouponPaymentDAO couponPaymentDAO) {
		this.couponPaymentDAO = couponPaymentDAO;
	}

	@POST
	@Path("/validateFormParamAndCharge")
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantCoupon validateFormParamAndCharge(
			@FormParam("") MerchantCoupon merchantCoupon) {
		return validateAndCharge(merchantCoupon);
	}

	@GET
	@Path("/validateMatrixParamAndCharge")
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantCoupon validateMatrixParamAndCharge(
			@MatrixParam("") MerchantCoupon merchantCoupon) {
		return validateAndCharge(merchantCoupon);
	}

	@GET
	@Path("/validateQueryParamAndCharge")
	@Produces(MediaType.APPLICATION_JSON)
	public MerchantCoupon validateQueryParamAndCharge(
			@QueryParam("") MerchantCoupon merchantCoupon) {
		return validateAndCharge(merchantCoupon);
	}

	private MerchantCoupon validateAndCharge(MerchantCoupon merchantCoupon) {	
		
		logger.info("MerchantCoupon : " + merchantCoupon);

		merchantCoupon.setTransDate(DateUtil.getCurrentDate());
		Merchant merchant = merchantCoupon.getMerchant();
		Coupon coupon = merchantCoupon.getCoupon();
		String merchantID=null;
		String couponID=null;

		if(merchant!=null){
			
			merchantID=merchant.getMerchantID();
			System.out.println("******After merchantID : " + merchantID);
		}
		if(coupon!=null){
			
			couponID=coupon.getCouponID();
			System.out.println("******After couponID : " + couponID);
		}
		
		couponPaymentDAO.save(merchantCoupon);

		merchant = merchantCoupon.getMerchant();
		coupon = merchantCoupon.getCoupon();
		Status merchantCouponStatus = merchantCoupon.getStatus();

		boolean checkStatus = true;
		
		

		if (merchantCouponStatus == null) {
			merchantCouponStatus = new Status();
		}

		if (merchant == null) {
			merchantCouponStatus.setStatusID("MNF");
			checkStatus = false;
		} else if (coupon == null) {
			merchantCouponStatus.setStatusID("CNF");
			checkStatus = false;
		}
		
		

		if (checkStatus) {
			Status merchantStatus = merchant.getStatus();
			Status couponStatus = coupon.getStatus();

			if (CouponConstants.INACTIVE.equals(merchantStatus.getStatusID())) {
				merchantCouponStatus.setStatusID("MI");
			} else if (CouponConstants.INVALID.equals(couponStatus
					.getStatusID())) {
				merchantCouponStatus.setStatusID("CI");
			} else if (CouponConstants.PAID.equals(couponStatus.getStatusID())) {
				merchantCouponStatus.setStatusID("CAP");
			} else if (coupon.getExpiryDate() != null
					&& coupon.getExpiryDate().getTime() < DateUtil
							.getCurrentDate().getTime()) {
				merchantCouponStatus.setStatusID("CE");
				couponStatus.setStatusID("EXP");
			} else {
				merchantCouponStatus.setStatusID("CV");
				couponStatus.setStatusID("P");
			}

			coupon.setStatus(couponStatus);

			couponPaymentDAO.update(coupon);
		}

		merchantCoupon.setStatus(merchantCouponStatus);
		
		if(merchant==null){
			merchant=new Merchant();
			merchant.setMerchantID(merchantID);
			merchantCoupon.setMerchant(merchant);
		}
		
		System.out.println("After coupon : " + merchantCoupon);
		if(coupon==null){
			coupon=new Coupon();
			coupon.setCouponID(couponID);
			merchantCoupon.setCoupon(coupon);
		}

		System.out.println("After MerchantCoupon : " + merchantCoupon);
		couponPaymentDAO.saveOrUpdate(merchantCoupon);

		logger.info("After MerchantCoupon : " + merchantCoupon);

		return merchantCoupon;
	}

}
