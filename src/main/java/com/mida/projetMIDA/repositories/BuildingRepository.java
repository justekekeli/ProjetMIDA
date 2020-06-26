package com.mida.projetMIDA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mida.projetMIDA.models.Building;

public interface BuildingRepository extends JpaRepository<Building,Long> {
	Building findByName(String name);
}
