package com.mida.projetMIDA.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mida.projetMIDA.models.Visit;
import com.mida.projetMIDA.services.VisitService;

@Controller
@RequestMapping("/Visites")
public class VisitController {

	@Autowired
	private VisitService service;
	
	@GetMapping("/liste-visites")
    public String showVisits(ModelMap model) {
		List<Visit> visits = service.getVisits();
        model.put("visits",visits);
        return "visites";
    }
	 @GetMapping(value = "/visite-ajout")
	    public String showAddVisitPage(ModelMap model) {
	        model.addAttribute("visit", new Visit());
	        return "visite";
	    }

	    @DeleteMapping(value = "/liste-visites")
	    public String deleteVisit(@RequestParam long id) {
	        service.deleteVisit(id);
	        return "visites";
	    }

	    @RequestMapping(value = "/visite/{id}", method = RequestMethod.GET)
	    public String showUpdateVisitPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Visit v = service.getVisitById(id).get();
	        model.put("visit",v);
	        return "visite";
	    }
	    @PutMapping(value = "/visite/{id}")
	    public String updateVisit(ModelMap model,@PathVariable(value="id") Long visit_id, @Valid @RequestBody Visit visit, BindingResult result) {

	        if (result.hasErrors()) {
	            return "visite";
	        }
	        service.updateVisit(visit_id,visit);
	        return "redirect:/liste";
	    }
	    @PostMapping(value = "/visite-ajout")
	    public String addVisit(ModelMap model, @Valid @RequestBody Visit visit, BindingResult result) {

	        if (result.hasErrors()) {
	            return "visit";
	        }
	        service.saveVisit(visit);
	        return "redirect:/liste";
	    }
}
