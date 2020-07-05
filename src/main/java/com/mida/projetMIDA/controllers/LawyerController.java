package com.mida.projetMIDA.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.mida.projetMIDA.models.Lawyer;
import com.mida.projetMIDA.services.LawyerService;

@Controller
public class LawyerController {

	@Autowired
	private LawyerService service;
	
	@GetMapping("/liste-avocats")
    public String showLawyers(ModelMap model) {
		List<Lawyer> lawyers = service.getLawyers();
        model.put("lawyers",lawyers);
        return "avocats";
    }
	 @GetMapping("/avocat-ajout")
	    public String showAddLawyerPage(ModelMap model) {
	        model.addAttribute("lawyer", new Lawyer());
	        model.addAttribute("type",null);
	        return "avocat";
	    }

	    @GetMapping( "/liste-avocats/{id}")
	    public String deleteLawyer(@PathVariable(value="id") Long id) {
	        service.deleteLawyer(id);
	        return "redirect:/liste-avocats";
	    }

	    @GetMapping("/avocat/{id}")
	    public String showUpdateLawyerPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Lawyer v = service.getLawyerById(id).get();
	        model.put("lawyer",v);
	        model.addAttribute("type","edit");
	        return "avocat";
	    }
	    @RequestMapping(value = "/avocat/{id}")
	    public String updateLawyer(ModelMap model,@PathVariable(value="id") Long lawyer_id, @Valid Lawyer u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "avocat";
	        }
	        service.updateLawyer(lawyer_id,u);
	        return "redirect:/liste-avocats";
	    }
	    @PostMapping(value = "/avocat-ajout")
	    public String addLawyer(ModelMap model, @Valid Lawyer u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "avocat";
	        }
	        service.saveLawyer(u);
	        return "redirect:/liste-avocats";
	    }
}
