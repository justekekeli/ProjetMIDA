package com.mida.projetMIDA.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="apartments")
public class Apartment  {

	//informations relatives à un appartement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idApartment;
	
	private int number;
	private int surface;
	private int rooms;
	private String price;
	private boolean stateApart;
	private Date updatedDate;
	
	//un appartement est appartient à un immeuble
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_id")
	private Building building;
	//un client peut visiter plusieurs appartements
	@OneToMany(mappedBy = "apart", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Visit> visits;
	//getters et setters
	
	public int getSurface() {
		return surface;
	}
	public Long getIdApartment() {
		return idApartment;
	}
	public void setIdApartment(Long idApartment) {
		this.idApartment = idApartment;
	}
	public void setSurface(int surface) {
		this.surface = surface;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean getStateApart() {
		return stateApart;
	}
	public void setStateApart(boolean stateApart) {
		this.stateApart = stateApart;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
