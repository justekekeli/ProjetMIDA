package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Agreement_selling")
public class Agreement_selling {

	//informations sur une promesse de vente d'appartement établi avec un client
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long idAgreement;
		private double finalPrice;
		private double advanced;
		private Date createdDate;
		private Date updatedDate;
		private String state;
		
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
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
}
