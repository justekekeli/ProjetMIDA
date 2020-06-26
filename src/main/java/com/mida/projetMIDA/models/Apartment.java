package com.mida.projetMIDA.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Apartment")
public class Apartment  {

	//informations relatives à un appartement
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idApartment;
	
	private int number;
	private int surface;
	private int rooms;
	private String price;
	private boolean stateApart=false;
	private Date updatedDate;
	
	//un appartement est attribué à un employé
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
	//un appartement est appartient à un immeuble
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "building_id")
	private Building building;
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
	public boolean isStateApart() {
		return stateApart;
	}
	public void setStateApart(boolean stateApart) {
		this.stateApart = stateApart;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
