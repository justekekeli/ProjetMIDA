package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Visit;
import com.mida.projetMIDA.repositories.VisitRepository;

@Service
public class VisitService {

	@Autowired
	private VisitRepository repo;
	

	public List<Visit> getVisits() {
        return repo.findAll();
    }
    public Optional<Visit> getVisitById(Long id) {
        return repo.findById(id);
    }

    public void updateVisit(Long id,Visit v) {
    	Visit u=repo.findById(id).orElse(null);
    	u.setDateVisit(v.getDateVisit());
    	u.setInteressed(v.isInteressed());
    	u.setRemark(v.getRemark());
        repo.save(u);
    }

    public void addVisit(Visit v) {
        repo.save(v);
    }
    public void deleteVisit(Long id) {
        Optional <Visit> v = repo.findById(id);
        if (v.isPresent()) {
            repo.delete(v.get());
        }
    }
    public void saveVisit(Visit v) {
        repo.save(v);
    }
}
