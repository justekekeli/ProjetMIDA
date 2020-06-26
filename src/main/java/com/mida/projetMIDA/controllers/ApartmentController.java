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

import com.mida.projetMIDA.models.Apartment;
import com.mida.projetMIDA.models.Building;
import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.services.ApartementService;
import com.mida.projetMIDA.services.BuildingService;
import com.mida.projetMIDA.services.UserService;

@Controller
public class ApartmentController {
	@Autowired
	private ApartementService service;
	@Autowired
	private BuildingService bservice;
	@Autowired
	private UserService uservice;
	
	@GetMapping("/liste-appartements")
    public String showApartments(ModelMap model) {
		List<Apartment> apartments = service.getApartments();
        model.put("apartments",apartments);
        //ajout
        model.addAttribute("apartment", new Apartment());
        model.addAttribute("type",null);
        model.addAttribute("users", uservice.getUsers());
        model.addAttribute("buildings", bservice.getBuildings());
        return "appartements";
    }

	    @GetMapping( "/liste-appartements/{id}")
	    public String deleteApart(@PathVariable(value="id") Long id) {
	        service.deleteApartment(id);
	        return "redirect:/liste-appartements";
	    }

	    @GetMapping("/appartement/{id}")
	    public String showUpdateApartPage(@PathVariable(value="id") Long id, ModelMap model) {
	       Apartment apartment = service.getApartmentById(id).get();
	       model.put("apartments",service.getApartments());
	        model.put("apartment",apartment);
	        model.addAttribute("type","edit");
	        return "appartements";
	    }
	    @RequestMapping(value = "/appartement/{id}")
	    public String updateApart(ModelMap model,@PathVariable(value="id") Long apart_id, @Valid Apartment u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "appartement";
	        }
	        service.updateApart(apart_id, u);
	        return "redirect:/liste-appartements";
	    }
	    @PostMapping(value = "/appartement-ajout")
	    public String addApart(ModelMap model, @Valid Apartment u,@RequestParam String mail,@RequestParam String name,@RequestParam int step,@RequestParam int num, BindingResult result) {

	        if (result.hasErrors()) {
	            return "appartement";
	        }
	        String numString=step+""+num;
	        int numero=Integer.parseInt(numString);
	        u.setNumber(numero);
	        User user= uservice.getUsersByEmail(mail);
	        u.setUser(user);
	        Building building=bservice.getBuildingByName(name);
	        u.setBuilding(building);
	        service.addApart(u);
	        return "redirect:/liste-appartements";
	    }
}
