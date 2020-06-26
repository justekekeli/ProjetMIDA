package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Lawyer;
import com.mida.projetMIDA.repositories.LawyerRepository;

@Service
public class LawyerService {
	@Autowired
	private LawyerRepository repo;
	
		public List <Lawyer> getLawyers() {
			return repo.findAll();
		}
		public List <Lawyer> getLawyersBySurname(String name) {
	        return repo.findBySurname(name);
	    }

	    public Optional<Lawyer> getLawyerById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateLawyer(Long id,Lawyer l) {
	    	Lawyer u=repo.findById(id).orElse(null);
	    	u.setAddress(l.getAddress());
	    	u.setAutorisation_num(l.getAutorisation_num());
	    	u.setFirstname(l.getFirstname());
	    	u.setSurname(l.getSurname());
	    	u.setTel1(l.getTel1());
	    	u.setTel2(l.getTel2());
	    	u.setTel3(l.getTel3());
	        repo.save(u);
	    }

	    public void addLawyer(Lawyer l) {
	        repo.save(l);
	    }
	    public void deleteLawyer(Long id) {
	        Optional <Lawyer> l = repo.findById(id);
	        if (l.isPresent()) {
	            repo.delete(l.get());
	        }
	    }
	    public void saveLawyer(Lawyer l) {
	        repo.save(l);
	    }
}
