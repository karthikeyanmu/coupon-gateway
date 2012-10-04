package com.intuit.coupongateway.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "MERCHANT_COUPON")
@XmlRootElement(name = "merchantCoupon")
public class MerchantCoupon extends BaseVO implements Serializable {

	private static final long serialVersionUID = 8998561344300239407L;

	private long orderNo;
	private Merchant merchant;
	private Coupon coupon;
	private Date transDate;
	private Status status;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ORDER_NO")
	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MERCHANT_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUPON_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	@Column(name = "TRANS_DATE")
	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STATUS")
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MerchantCoupon [orderNo=" + orderNo + ", merchant=" + merchant
				+ ", coupon=" + coupon + ", transDate=" + transDate
				+ ", status=" + status + "]";
	}

}
