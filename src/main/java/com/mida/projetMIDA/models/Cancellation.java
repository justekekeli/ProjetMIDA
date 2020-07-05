package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cancellations")
public class Cancellation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCancel;
	private Date cancelledDate;
	@Lob
	private String reason;
	
	//une promesse de vente peut être annulée---info sur l'annulation
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "agreement_id", nullable = false)
	 private Agreement_selling agreement;

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
	public Agreement_selling getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement_selling agreement) {
		this.agreement = agreement;
	}
	
}
