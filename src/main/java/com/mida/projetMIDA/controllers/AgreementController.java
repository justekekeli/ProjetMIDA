package com.mida.projetMIDA.controllers;

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

import com.mida.projetMIDA.AgreementState;
import com.mida.projetMIDA.Utils;
import com.mida.projetMIDA.models.Agreement_selling;
import com.mida.projetMIDA.models.Cancellation;
import com.mida.projetMIDA.models.Visit;
import com.mida.projetMIDA.services.Agreement_sellingService;
import com.mida.projetMIDA.services.CancellationService;
import com.mida.projetMIDA.services.LawyerService;
import com.mida.projetMIDA.services.VisitService;


@Controller
@RequestMapping("Promesse")
public class AgreementController {

	@Autowired
	private Agreement_sellingService service;
	@Autowired
	private LawyerService lservice;
	@Autowired
	private VisitService vservice;
	@Autowired
	private CancellationService cservice;

	@GetMapping(value = {"/liste-promesses", "/liste-promesses/{cin}/{visite}/{immeuble}/{apart}"})
    public String showAgreements(@PathVariable(name="cin", required = false) String cin,
    		@PathVariable(name="visite", required = false) String visite,
    		@PathVariable(name="immeuble", required = false) String immeuble,
    		@PathVariable(name="apart", required = false) String apart,@RequestParam(name="info",defaultValue = "") String info,
    		ModelMap model
    		) {
		model.addAttribute("agreement", new Agreement_selling());
		model.addAttribute("type",null);
		model.addAttribute("lawyers", lservice.getLawyers());
        model.put("agreements",service.getAgreement());
        model.addAttribute("info",info);
        return "promesses";
    }

    @GetMapping(value = "/liste-promesses/{id}")
    public String deleteAgreement(@PathVariable(value="id") Long id,RedirectAttributes attr) {
        service.deleteAgreement(id);
        attr.addAttribute("info", "supprimer");
        return "redirect:/Promesse/liste-promesses";
    }

    @RequestMapping(value = "/promesse/{id}", method = RequestMethod.GET)
    public String showUpdateAgreementPage(@PathVariable(value="id") Long id, ModelMap model) {
        Agreement_selling v = service.getAgreementById(id).get();
        model.put("cin",v.getVisit().getCustomer().getCin());
        model.put("building",v.getVisit().getApart().getBuilding().getName());
        model.put("number",v.getVisit().getApart().getNumber());
        model.put("visit_id",v.getVisit().getId());
        model.put("agreement",v);
        model.addAttribute("type","edit");
		model.addAttribute("lawyers", lservice.getLawyers());
        model.put("agreements",service.getAgreement());
        return "promesses";
    }
    @RequestMapping(value = "/promesse/{id}")
    public String updateAgreement(@PathVariable(value="id") Long id, @RequestParam Long lawyer, @RequestParam String reason, @RequestParam Long visit_id,@Valid Agreement_selling ag, BindingResult result,RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "promesses";
        }
        if(reason != "") {
            Cancellation cancel = new Cancellation();
            cancel.setCancelledDate(Utils.setDate());
            cancel.setAgreement(service.getAgreementById(id).get());
            cancel.setReason(reason);
            cservice.addCancel(cancel);
        }
        ag.setUpdatedDate(Utils.setDate());
        Visit visit= vservice.getVisitById(visit_id).get();
        ag.setLawyer(lservice.getLawyerById(lawyer).get());
        ag.setVisit(visit);
        if(ag.getState()==AgreementState.CONCLU) {
            visit.getApart().setStateApart(true);
        }
        service.updateAgreement(id, ag);
        attr.addAttribute("info", "editer");
        return "redirect:/Promesse/liste-promesses";
    }
    @PostMapping(value = "/promesse-ajout")
    public String addAgreement( @Valid Agreement_selling ag, @RequestParam Long lawyer, @RequestParam Long visit_id, BindingResult result,RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "promesses";
        }
        ag.setCreatedDate(Utils.setDate());
        ag.setLawyer(lservice.getLawyerById(lawyer).get());
        ag.setVisit(vservice.getVisitById(visit_id).get());
        service.addAgreement(ag);
        attr.addAttribute("info", "creer");
        return "redirect:/Promesse/liste-promesses";
    }
}
