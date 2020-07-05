package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Apartment;
import com.mida.projetMIDA.repositories.ApartmentRepository;

@Service
public class ApartementService {
	@Autowired
	private ApartmentRepository repo;

	 	public List<Apartment> getApartments() {
	        return repo.findAll();
	    }
	 	public List<Apartment> findByNumber(int number) {
	        return repo.findByNumber(number);
	    }
	    public Optional<Apartment> getApartmentById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateApart(Long id,Apartment a) {
	    	Apartment e=repo.findById(id).orElse(null);
	    	e.setPrice(a.getPrice());
	    	e.setRooms(a.getRooms());
	    	e.setStateApart(a.getStateApart());
	    	e.setSurface(a.getSurface());
	    	e.setNumber(a.getNumber());
	    	e.setUpdatedDate(a.getUpdatedDate());
	    	repo.save(e);
	    }

	    public void addApart(Apartment a) {
	        repo.save(a);
	    }
	    public void deleteApartment(Long id) {
	        Optional <Apartment> a = repo.findById(id);
	        if (a.isPresent()) {
	            repo.delete(a.get());
	        }
	    }
	    public void saveApartment(Apartment a) {
	        repo.save(a);
	    }
}
