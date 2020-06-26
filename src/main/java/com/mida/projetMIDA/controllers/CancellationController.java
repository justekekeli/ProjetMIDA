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

import com.mida.projetMIDA.models.Cancellation;
import com.mida.projetMIDA.services.CancellationService;

@Controller
@RequestMapping("/Désistements")
public class CancellationController {
	@Autowired
	private CancellationService service;
	
	@GetMapping("/liste-annulations")
    public String showCancels(ModelMap model) {
		List<Cancellation> cancels = service.getCancels();
        model.put("cancels",cancels);
        return "desistements";
    }
	 @GetMapping("/desistement-ajout")
	    public String showAddCancelsPage(ModelMap model) {
	        model.addAttribute("desistement", new 	Cancellation());
	        return "desistement";
	    }

	    @DeleteMapping( "/liste-annulations")
	    public String deleteCancel(@RequestParam long id) {
	        service.deleteCancel(id);
	        return "desistements";
	    }

	    @GetMapping("/desistement/{id}")
	    public String showUpdateCancelPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Cancellation v = service.getCancelById(id).get();
	        model.put("desistement",v);
	        return "desistement";
	    }
	    @PutMapping(value = "/desistement/{id}")
	    public String updateCancel(ModelMap model,@PathVariable(value="id") Long cancel_id, @Valid @RequestBody Cancellation u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "desistement";
	        }
	        service.updateCancel(cancel_id, u);
	        return "redirect:/liste";
	    }
	    @PostMapping(value = "/desistement-ajout")
	    public String addCancel(ModelMap model, @Valid @RequestBody Cancellation u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "desistement";
	        }
	        service.addCancel(u);
	        return "redirect:/liste";
	    }
}
