package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Building;
import com.mida.projetMIDA.repositories.BuildingRepository;

@Service
public class BuildingService {
	@Autowired
	private BuildingRepository repo;
	
		public Building getBuildingByName(String name) {
	        return repo.findByName(name);
	    }
		public List<Building> getBuildings() {
	        return repo.findAll();
	    }
	    public Optional<Building> getBuildingById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateBuilding(Long id,Building b) {
	    	Building i= repo.findById(id).orElse(null);
	    	i.setAddress(b.getAddress());
	    	i.setName(b.getName());
	        repo.save(i);
	    }

	    public void addBuilding(Building b) {
	        repo.save(b);
	    }
	    public void deleteBuilding(Long id) {
	        Optional <Building> b = repo.findById(id);
	        if (b.isPresent()) {
	            repo.delete(b.get());
	        }
	    }
	    public void saveBuilding(Building b) {
	        repo.save(b);
	    }
}
