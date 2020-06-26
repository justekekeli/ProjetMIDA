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
import org.springframework.web.bind.annotation.RequestParam;

import com.mida.projetMIDA.models.Agreement_selling;
import com.mida.projetMIDA.services.Agreement_sellingService;

@Controller
@RequestMapping("/Prommesses")
public class AgreementController {
	@Autowired
	private Agreement_sellingService service;
	
	@GetMapping("/liste-promesses")
    public String showAgreements(ModelMap model) {
		List<Agreement_selling> agreement_sellings = service.getAgreement();
        model.put("agreement_sellings",agreement_sellings);
        return "promesses";
    }
	 @GetMapping("/promesse-ajout")
	    public String showAddAgreementPage(ModelMap model) {
	        model.put("Agreement", new Agreement_selling());
	        return "promesse";
	    }

	    @DeleteMapping( "/liste-promesses")
	    public String deleteAgreement(@RequestParam long id) {
	        service.deleteAgreement(id);
	        return "promesses";
	    }

	    @GetMapping("/promesse/edition/{id}")
	    public String showUpdateAgreementPage(@PathVariable(value="id") Long id, ModelMap model) {
	       Agreement_selling v = service.getAgreementById(id).get();
	        model.put("agreement",v);
	        return "promesse";
	    }
	    @PutMapping(value = "/promesse/edition/{id}")
	    public String updateAgreement(ModelMap model,@PathVariable(value="id") Long ag_id, @Valid @RequestBody Agreement_selling u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "promesse";
	        }
	        service.updateAgreement(ag_id, u);
	        return "redirect:/liste";
	    }
	    @PostMapping(value = "/promesse-ajout")
	    public String addApart(ModelMap model, @Valid @RequestBody Agreement_selling u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "promesse";
	        }
	        service.addAgreement(u);
	        return "redirect:/liste";
	    }
}
