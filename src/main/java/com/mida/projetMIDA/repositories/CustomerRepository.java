package com.mida.projetMIDA.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.mida.projetMIDA.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Customer findByCin(String cin);
}
