package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.AgreementState;
import com.mida.projetMIDA.models.Agreement_selling;
import com.mida.projetMIDA.repositories.Agreement_sellingRepository;

@Service
public class Agreement_sellingService {
	@Autowired
	private Agreement_sellingRepository repo;
	
		public int lenghtList() {
			int i=0;
			for(Agreement_selling ag:this.getAgreement()) {
				if(ag.getState()==AgreementState.CONCLU) {
					i++;
				}
			}
			return i;
		}
	  public List<Agreement_selling> getAgreement() {
	        return repo.findAll();
	    }
	    public Optional<Agreement_selling> getAgreementById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateAgreement(Long id,Agreement_selling a) {
	    	Agreement_selling m=repo.findById(id).orElse(null);
	    	m.setAdvanced(a.getAdvanced());
	    	m.setCreatedDate(a.getCreatedDate());
	    	m.setFinalPrice(a.getFinalPrice());
	    	m.setState(a.getState());
	    	m.setUpdatedDate(a.getUpdatedDate());
	        repo.save(m);
	    }

	    public void addAgreement(Agreement_selling a) {
	        repo.save(a);
	    }
	    public void deleteAgreement(Long id) {
	        Optional <Agreement_selling> a = repo.findById(id);
	        if (a.isPresent()) {
	            repo.delete(a.get());
	        }
	    }
	    public void saveUser(Agreement_selling a) {
	        repo.save(a);
	    }
}
