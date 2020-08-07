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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mida.projetMIDA.Utils;
import com.mida.projetMIDA.models.Apartment;
import com.mida.projetMIDA.models.Visit;
import com.mida.projetMIDA.services.ApartementService;
import com.mida.projetMIDA.services.BuildingService;
import com.mida.projetMIDA.services.CustomerService;
import com.mida.projetMIDA.services.UserService;
import com.mida.projetMIDA.services.VisitService;

@Controller
@RequestMapping("Visite")
public class VisitController {

	@Autowired
	private VisitService service;
	@Autowired
	private BuildingService bservice;
	@Autowired
	private ApartementService aservice;
	@Autowired
	private CustomerService cservice;
	@Autowired
	private UserService uservice;

	@GetMapping(value = {"/liste-visites", "/liste-visites/{cinClient}"})
    public String showVisits(@PathVariable(name="cinClient", required = false) String cin,@RequestParam(name="info",defaultValue = "") String info,ModelMap model) {
		List<Visit> visits = service.getVisits();
		model.addAttribute("visit", new Visit());
		model.addAttribute("type",null);
		model.addAttribute("buildings", bservice.getBuildings());
		model.addAttribute("users", uservice.getUsers());
        model.put("visits",visits);
        model.addAttribute("info",info);
        return "visites";
    }

    @GetMapping(value = "/liste-visite/{id}")
    public String deleteVisit(@PathVariable(value="id") Long id,RedirectAttributes attr) {
        service.deleteVisit(id);
        attr.addAttribute("info", "supprimer");
        return "redirect:/Visite/liste-visites";
    }

    @RequestMapping(value = "/visite/{id}", method = RequestMethod.GET)
    public String showUpdateVisitPage(@PathVariable(value="id") Long id, ModelMap model) {
        Visit v = service.getVisitById(id).get();
        model.put("visit",v);
        model.addAttribute("type","edit");
        model.put("visits",service.getVisits());
        model.put("number",v.getApart().getNumber());
        model.put("cin",v.getCustomer().getCin());
        model.addAttribute("users", uservice.getUsers());
		model.addAttribute("buildings", bservice.getBuildings());
        return "visites";
    }
    @RequestMapping(value = "/visite/{id}")
    public String updateVisit(@PathVariable(value="id") Long visit_id, @RequestParam String cin,@RequestParam String mail, @RequestParam int numApart,@RequestParam String name,@Valid Visit visit, BindingResult result,RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "visites";
        }
        Apartment apart=null;
        List<Apartment> aparts= aservice.findByNumber(numApart);
        for(Apartment a:aparts) {
        	if(a.getBuilding()==bservice.getBuildingByName(name)) {
        		apart=a;
        	}
        }
        visit.setUserVisit(uservice.getUsersByEmail(mail));
        visit.setApart(apart);
        service.updateVisit(visit_id,visit);
        attr.addAttribute("info", "editer");
        return "redirect:/Visite/liste-visites";
    }
    @PostMapping(value = "/visite-ajout")
    public String addVisit(@Valid Visit visit, @RequestParam String cin,@RequestParam String mail,  @RequestParam int numApart,@RequestParam String name,BindingResult result,RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "visites";
        }
        Apartment apart=null;
        List<Apartment> aparts= aservice.findByNumber(numApart);
        for(Apartment a:aparts) {
        	if(a.getBuilding()==bservice.getBuildingByName(name)) {
        		apart=a;
        	}
        }
        visit.setDateVisit(Utils.setDate());
        visit.setUserVisit(uservice.getUsersByEmail(mail));
        visit.setCustomer(cservice.getCustomersByCin(cin));
        visit.setApart(apart);
        service.saveVisit(visit);
        attr.addAttribute("info", "creer");
        return "redirect:/Visite/liste-visites";
    }
}
