package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Visit")
public class Visit {

	//informations d'enregistrement d'une visite
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private Date dateVisit;
		private String remark;
		private boolean isInteressed;
	//getters et setters
		public Long getIdVisit() {
			return id;
		}
		public void setIdVisit(Long idVisit) {
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
		public boolean isInteressed() {
			return isInteressed;
		}
		public void setInteressed(boolean isInteressed) {
			this.isInteressed = isInteressed;
		}
		
}
