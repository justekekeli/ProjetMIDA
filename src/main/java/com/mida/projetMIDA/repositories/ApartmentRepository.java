package com.mida.projetMIDA.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mida.projetMIDA.models.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
	List<Apartment> findByNumber(int number);
}
