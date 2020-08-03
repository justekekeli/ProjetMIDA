package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mida.projetMIDA.AgreementState;

@Entity
@Table(name="agreement_sellings")
public class Agreement_selling{

	//informations sur une promesse de vente d'appartement établi avec un client
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long idAgreement;
		private double finalPrice;
		private double advanced;
		private Date createdDate;
		private Date updatedDate;
		@Enumerated(EnumType.STRING)
		private AgreementState state;

		//une vente fait intervenir un avocat
		@JsonIgnore
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "lawyer_id")
	    private Lawyer lawyer;
		//une promesse de vente fait suite à une visite
		@JsonIgnore
		@OneToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "visit_id", nullable = false)
	    private Visit visit;
		//Une promesse de vente peut être annulée
		@JsonIgnore
		@OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "agreement")
		private Cancellation cancel;
		
		//getters et setters
		public Long getIdAgreement() {
			return idAgreement;
		}
		public void setIdAgreement(Long idAgreement) {
			this.idAgreement = idAgreement;
		}
		public double getFinalPrice() {
			return finalPrice;
		}
		public void setFinalPrice(double finalPrice) {
			this.finalPrice = finalPrice;
		}
		public double getAdvanced() {
			return advanced;
		}
		public void setAdvanced(double advanced) {
			this.advanced = advanced;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
		public AgreementState getState() {
			return state;
		}
		public void setState(AgreementState state) {
			this.state = state;
		}
		public Visit getVisit() {
			return visit;
		}
		public void setVisit(Visit visit) {
			this.visit = visit;
		}
		public Lawyer getLawyer() {
			return lawyer;
		}
		public void setLawyer(Lawyer lawyer) {
			this.lawyer = lawyer;
		}
		public Cancellation getCancel() {
			return cancel;
		}
		public void setCancel(Cancellation cancel) {
			this.cancel = cancel;
		}
		
}
