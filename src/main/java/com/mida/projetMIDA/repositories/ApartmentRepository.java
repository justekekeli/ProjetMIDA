package com.mida.projetMIDA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mida.projetMIDA.models.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
}
