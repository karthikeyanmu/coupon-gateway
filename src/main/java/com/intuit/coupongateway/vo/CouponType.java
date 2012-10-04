package com.intuit.coupongateway.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COUPON_TYPE")
@XmlRootElement(name="couponType")
public class CouponType extends BaseVO implements Serializable {

	private static final long serialVersionUID = -3546462361401715286L;

	private long couponTypeID;
	private String couponTypeName;

	@Id
	@Column(name = "COUPON_TYPE_ID")
	public long getCouponTypeID() {
		return couponTypeID;
	}

	public void setCouponTypeID(long couponTypeID) {
		this.couponTypeID = couponTypeID;
	}

	@Column(name = "COUPON_TYPE_NAME")
	public String getCouponTypeName() {
		return couponTypeName;
	}

	public void setCouponTypeName(String couponTypeName) {
		this.couponTypeName = couponTypeName;
	}

	@Override
	public String toString() {
		return "CouponType [couponTypeID=" + couponTypeID + ", couponTypeName="
				+ couponTypeName + "]";
	}

}
