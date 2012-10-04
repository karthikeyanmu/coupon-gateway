package com.intuit.coupongateway.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MERCHANT")
@XmlRootElement(name="merchant")
public class Merchant extends BaseVO implements Serializable {

	private static final long serialVersionUID = -2033305437280360778L;

	private String merchantID;
	private String merchantName;
	private Status status;

	@Id
	@Column(name = "MERCHANT_ID")
	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	@Column(name = "MERCHANT_NAME")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
		return "Merchant [merchantID=" + merchantID + ", merchantName="
				+ merchantName + ", status=" + status + "]";
	}

}
