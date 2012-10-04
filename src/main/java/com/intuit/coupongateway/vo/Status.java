package com.intuit.coupongateway.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "STATUS")
@XmlRootElement(name="status")
public class Status extends BaseVO implements Serializable {

	private static final long serialVersionUID = 8374044253420258118L;

	private String statusID;
	private String statusName;

	@Id
	@Column(name = "STATUS_ID")
	public String getStatusID() {
		return statusID;
	}

	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}

	@Column(name = "STATUS_NAME")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "Status [statusID=" + statusID + ", statusName=" + statusName
				+ "]";
	}

	
}
