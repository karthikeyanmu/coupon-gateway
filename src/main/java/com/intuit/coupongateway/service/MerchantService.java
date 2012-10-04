package com.intuit.coupongateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.log4j.Logger;

import com.intuit.coupongateway.constant.CouponConstants;
import com.intuit.coupongateway.dao.MerchantDAO;
import com.intuit.coupongateway.vo.Merchant;
import com.intuit.coupongateway.vo.MerchantCoupon;

@Path("/merchantService")
public class MerchantService extends BaseService implements IMerchantService{

	private Logger logger=Logger.getLogger(MerchantService.class);
	private MerchantDAO merchantDAO;
	
	@Context
	private MessageContext messageContext;
	
	public MerchantDAO getMerchantDAO() {
		return merchantDAO;
	}


	public void setMerchantDAO(MerchantDAO merchantDAO) {
		this.merchantDAO = merchantDAO;
	}


	@Override
	@POST
	@Path("/authenticateMerchant")
	@Produces(MediaType.APPLICATION_JSON)
	public Merchant authenticateMerchant(@FormParam("")Merchant merchant) {
		logger.info("merchant  Login ID "+merchant);
		merchant=(Merchant) merchantDAO.load("from Merchant merchant where merchant.merchantID=?", new Object[]{merchant.getMerchantID()});
		logger.info("merchant "+merchant);
		return merchant;
	}

	@Override
	@POST
	@Path("/getCouponList")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getCouponList(@FormParam("")MerchantCoupon merchantCoupon) {
		logger.info("merchant  getCouponList "+merchantCoupon);
		List<MerchantCoupon> merchantCoupons=null;
		List<Object> values=new ArrayList<Object>();
		StringBuffer buffer=new StringBuffer("SELECT MC.MERCHANT_ID,MC.COUPON_ID,S.STATUS_NAME,MC.TRANS_DATE,MC.ORDER_NO,C.COUPON_VALUE FROM MERCHANT_COUPON MC LEFT JOIN MERCHANT M ON MC.MERCHANT_ID=M.MERCHANT_ID LEFT OUTER JOIN COUPON C ON MC.COUPON_ID=C.COUPON_ID LEFT OUTER JOIN STATUS S  ON MC.STATUS=S.STATUS_ID WHERE MC.MERCHANT_ID=? ");
		values.add(merchantCoupon.getMerchant().getMerchantID());
		
		
		if(merchantCoupon.getOrderNo()!=0){
			buffer.append("and MC.ORDER_NO=? ");
			values.add(merchantCoupon.getOrderNo());
		}		
		
		
		buffer.append("ORDER BY MC.TRANS_DATE DESC");
		
		merchantCoupons= merchantDAO.loadAllCoupons(buffer.toString(),values.toArray());
		logger.info("merchant "+merchantCoupons);	
		
		
		return populateSearchResultsMap(merchantCoupons);
	}
}
