package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="_visits")
public class Visit {

	//informations d'enregistrement d'une visite
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private Date dateVisit;
		@Lob
		private String remark;
		private boolean isInteressed;
		//Une visite concerne un client
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;
		//Une visite concerne un appartement
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "apart_id", nullable = false)
	    private Apartment apart;
		//Une visite peut donner suite à une promesse de vente
		@OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "visit")
	    private Agreement_selling agreement;
		//une visite  est attribué à un employé
		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
	    private User userVisit;
	//getters et setters
		public Long getId() {
			return id;
		}
		public void setId(Long idVisit) {
			this.id = idVisit;
		}
		public Date getDateVisit() {
			return dateVisit;
		}
		public void setDateVisit(Date dateVisit) {
			this.dateVisit = dateVisit;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public boolean getIsInteressed() {
			return isInteressed;
		}
		public void setisInteressed(boolean isInteressed) {
			this.isInteressed = isInteressed;
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		public Apartment getApart() {
			return apart;
		}
		public void setApart(Apartment apart) {
			this.apart = apart;
		}
		public Agreement_selling getAgreement() {
			return agreement;
		}
		public void setAgreement(Agreement_selling agreement) {
			this.agreement = agreement;
		}
		public User getUserVisit() {
			return userVisit;
		}
		public void setUserVisit(User user) {
			this.userVisit = user;
		}
		
}
