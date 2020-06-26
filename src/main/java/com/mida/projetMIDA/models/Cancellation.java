package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cancellation")
public class Cancellation {
	@Id
	@GeneratedValue
    private Long idCancel;
	private Date cancelledDate;
	private String reason;
	
	//getters et setters
	public Long getIdCancel() {
		return idCancel;
	}
	public void setIdCancel(Long idCancel) {
		this.idCancel = idCancel;
	}
	public Date getCancelledDate() {
		return cancelledDate;
	}
	public void setCancelledDate(Date cancelledDate) {
		this.cancelledDate = cancelledDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
