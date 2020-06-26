package com.mida.projetMIDA.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Lawyer")
public class Lawyer {

	//informations sur un avocat travaillant avec la société
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLawyer;
	private String surname;
	private String firstname;
	private String address;
	private String tel1;
	private String tel2;
	private String tel3;
	private String autorisation_num;
	
	public Long getIdLawyer() {
		return idLawyer;
	}
	public void setIdLawyer(Long idLawyer) {
		this.idLawyer = idLawyer;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getTel2() {
		return tel2;
	}
	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}
	public String getTel3() {
		return tel3;
	}
	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}
	public String getAutorisation_num() {
		return autorisation_num;
	}
	public void setAutorisation_num(String autorisation_num) {
		this.autorisation_num = autorisation_num;
	}
	
	
			
}
