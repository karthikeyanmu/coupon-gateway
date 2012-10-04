package com.intuit.coupongateway.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.intuit.coupongateway.service.CouponPayment;
import com.intuit.coupongateway.service.MerchantService;
import com.intuit.coupongateway.vo.Coupon;
import com.intuit.coupongateway.vo.CouponType;
import com.intuit.coupongateway.vo.Merchant;
import com.intuit.coupongateway.vo.MerchantCoupon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/config/application-context.xml" })
public class TestDAO {

	@Autowired
	private BaseDAO baseDAO;
	
	@Autowired
	private CouponPayment couponPayment;
	
	@Autowired
	private MerchantService merchantService;
	
	
	@Test
	public void testLoad(){
		/*List<CouponType> couponTypes=(List<CouponType>) baseDAO.loadAll("from MerchantCoupon mc where mc.merchant.merchantID=4266961000520001");
		System.out.println(couponTypes);*/
		MerchantCoupon merchantCoupon=new MerchantCoupon();
		
		Merchant merchant=new Merchant();
		merchant.setMerchantID("4266961000520001");
		merchantCoupon.setMerchant(merchant);
		
		Coupon coupon=new Coupon();
		coupon.setCouponID("1234567890987654321");
		merchantCoupon.setCoupon(coupon);
		
		merchantCoupon.setTransDate(new Date());
		//merchantCoupon.setOrderNo(30);
		
		baseDAO.save(merchantCoupon);
		//couponPayment.validateFormParamAndCharge(merchantCoupon);
		
		System.out.println(merchantCoupon);
	}
	
	
	
}
