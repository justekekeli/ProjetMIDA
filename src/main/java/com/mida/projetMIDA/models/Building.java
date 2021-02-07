package com.mida.projetMIDA.models;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="_buildings")
public class Building {

	//informations relatives à un immeuble de la société
		@Id
		@GeneratedValue
		private Long idBuilding;
		private String name;
		private String address;
		
		//Un immeuble contient 1 ou plusieurs appartements
		@OneToMany(mappedBy = "building", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private List<Apartment> apartments;
	//getters et setters
		public Long getIdBuilding() {
			return idBuilding;
		}
		public void setIdBuilding(Long idBuilding) {
			this.idBuilding = idBuilding;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public List<Apartment> getApartments() {
			return apartments;
		}
		public void addApartments(Apartment apartment) {
			apartment.setBuilding(this);
			this.apartments.add(apartment);
		}
		public void setApartments(List<Apartment> apartments2) {
			this.apartments=apartments2;
			for(Apartment t:apartments) {
				t.setBuilding(this);
			}
			
		}
}
