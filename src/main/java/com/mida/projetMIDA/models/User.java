package com.mida.projetMIDA.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	//informations sur un utilisateur de l'application
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;
	private String surname;
	private String firstname;
	private String email;
	private String pass;
	private String address;
	private Date createdDate;
	//un utilisateur peut avoir plusieurs roles
	@ManyToMany(cascade=CascadeType.ALL,fetch =FetchType.EAGER) 
	@JoinTable(
			name="roles_users",
			joinColumns= @JoinColumn(name="user_id"),
			inverseJoinColumns= @JoinColumn(name="role_id")
			)
	  private List<Role> roles =new ArrayList<>();
	
	//Un utilisateur peut gérer 1 ou plusieurs clients
	@OneToMany(mappedBy = "userCustomer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Customer> customers;
	//Un utilisateur non-admin peut gérer 1 ou plusieurs appartements
	@OneToMany(mappedBy = "userVisit", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Visit> visits;

	//getters et setters (pour respectivement récupérer et modifier les infos)
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String password) {
		this.pass = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public void addCustomers(Customer customers) {
		this.customers.add(customers);
	}
	
	public List<Visit> getApartments() {
		return visits;
	}
	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	public void addVisits(Visit v) {
		this.visits.add(v);
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
