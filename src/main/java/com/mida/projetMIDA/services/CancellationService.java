package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Cancellation;
import com.mida.projetMIDA.repositories.CancellationRepository;

@Service
public class CancellationService {
	@Autowired
	private CancellationRepository repo;
	
		public List <Cancellation> getCancels() {
			return repo.findAll();
		}

	    public Optional<Cancellation> getCancelById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateCancel(Long id,Cancellation c) {
	    	Cancellation o= repo.findById(id).orElse(null);
	    	o.setCancelledDate(c.getCancelledDate());
	    	o.setReason(c.getReason());
	        repo.save(o);
	    }

	    public void addCancel(Cancellation c) {
	        repo.save(c);
	    }
	    public void deleteCancel(Long id) {
	        Optional <Cancellation> c = repo.findById(id);
	        if (c.isPresent()) {
	            repo.delete(c.get());
	        }
	    }
	    public void saveCancel(Cancellation c) {
	        repo.save(c);
	    }
}
