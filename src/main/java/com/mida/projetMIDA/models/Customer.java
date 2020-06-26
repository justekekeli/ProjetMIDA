package com.mida.projetMIDA.models;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {

	//informations relatives à un client qui est intéréssé
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCustomer;
	private String cin;
	private String surname;
	private String firstname1;
	private String firstname2;
	private String addressCustomer;
	private String tel;
	private String work;
	//un client est géré par un employé
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCustomer_id")
    private User userCustomer;

	//getters et setters 
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFirstname1() {
		return firstname1;
	}
	public void setFirstname1(String firstname1) {
		this.firstname1 = firstname1;
	}
	public String getFirstname2() {
		return firstname2;
	}
	public void setFirstname2(String firstname2) {
		this.firstname2 = firstname2;
	}
	public String getAddressCustomer() {
		return addressCustomer;
	}
	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public User getUserCustomer() {
		return userCustomer;
	}
	public void setUserCustomer(User userCustomer) {
		this.userCustomer = userCustomer;
	}
	

			
	
}
