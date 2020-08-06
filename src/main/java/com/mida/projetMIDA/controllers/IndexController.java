package com.mida.projetMIDA.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.mida.projetMIDA.services.Agreement_sellingService;
import com.mida.projetMIDA.services.BuildingService;
import com.mida.projetMIDA.services.CustomerService;
import com.mida.projetMIDA.services.UserService;


@Controller
public class IndexController {
	@Autowired
	private BuildingService bservice;
	@Autowired
	private UserService uservice;
	@Autowired
	private CustomerService cservice;
	@Autowired
	private Agreement_sellingService aservice;

	@GetMapping("/")
    public String statistics(ModelMap model) {
		model.addAttribute("sizeBuilding", bservice.lenghtList());
		model.addAttribute("sizeUser", uservice.lenghtList());
		model.addAttribute("sizeCustomer", cservice.lenghtList());
		model.addAttribute("sizeAgreement", aservice.lenghtList());
        return "index";
    }
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
