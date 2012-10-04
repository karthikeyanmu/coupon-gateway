package com.intuit.coupongateway.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "COUPON")
@XmlRootElement(name = "coupon")
public class Coupon extends BaseVO {

	private static final long serialVersionUID = 8625121856515904145L;

	private String couponID;
	private int couponTypeID;
	private Date expiryDate;
	private Status status;
	private int couponValue;

	@Id
	@Column(name = "COUPON_ID")
	public String getCouponID() {
		return couponID;
	}

	public void setCouponID(String couponID) {
		this.couponID = couponID;
	}

	@Column(name = "COUPON_TYPE_ID")
	public int getCouponTypeID() {
		return couponTypeID;
	}

	public void setCouponTypeID(int couponTypeID) {
		this.couponTypeID = couponTypeID;
	}

	@Column(name = "EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUS")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Column(name = "COUPON_VALUE")
	public int getCouponValue() {
		return couponValue;
	}

	public void setCouponValue(int couponValue) {
		this.couponValue = couponValue;
	}

	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", couponTypeID="
				+ couponTypeID + ", expiryDate=" + expiryDate + ", status="
				+ status + ", couponValue=" + couponValue + "]";
	}

}
