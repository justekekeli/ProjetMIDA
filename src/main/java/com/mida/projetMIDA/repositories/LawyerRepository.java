package com.mida.projetMIDA.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.mida.projetMIDA.models.Lawyer;

public interface LawyerRepository extends JpaRepository<Lawyer,Long> {
	List <Lawyer> findBySurname(String name);
}
