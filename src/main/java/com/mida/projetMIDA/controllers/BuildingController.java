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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mida.projetMIDA.models.Building;
import com.mida.projetMIDA.services.BuildingService;

@Controller
@RequestMapping("Immeuble")
public class BuildingController {
	@Autowired
	private BuildingService service;
	
	@GetMapping("/liste-immeubles")
    public String showBuildings(@RequestParam(name="info",defaultValue = "") String info,ModelMap model) {
		List<Building> buildings = service.getBuildings();
        model.put("buildings",buildings);
        model.put("info",info);
        return "immeubles";
    }
	 @GetMapping("/immeuble-ajout")
	    public String showAddBuildingPage(ModelMap model) {
	        model.addAttribute("building", new 	Building());
	        model.addAttribute("type",null);
	        return "immeuble";
	    }

	    @GetMapping( "/liste-immeubles/{id}")
	    public String deleteBuilding(@PathVariable(value="id") Long id,RedirectAttributes attr) {
	        service.deleteBuilding(id);
	        attr.addAttribute("info", "supprimer");
	        return "redirect:/liste-immeubles";
	    }

	    @GetMapping("/immeuble/{id}")
	    public String showUpdateBuildingPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Building building = service.getBuildingById(id).get();
	        model.put("building",building);
	        model.addAttribute("type","edit");
	        return "immeuble";
	    }
	    @RequestMapping(value = "/immeuble/{id}")
	    public String updateBuilding(@PathVariable(value="id") Long build_id, @Valid Building u, BindingResult result,RedirectAttributes attr) {

	        if (result.hasErrors()) {
	            return "immeuble";
	        }
	        service.updateBuilding(build_id, u);
	        attr.addAttribute("info", "editer");
	        return "redirect:/liste-immeubles";
	    }
	    @PostMapping(value = "/immeuble-ajout")
	    public String addBuilding( @Valid Building u, BindingResult result,RedirectAttributes attr) {

	        if (result.hasErrors()) {
	            return "immeuble";
	        }
	        service.addBuilding(u);
	        attr.addAttribute("info", "creer");
	        return "redirect:/liste-immeubles";
	    }
}
