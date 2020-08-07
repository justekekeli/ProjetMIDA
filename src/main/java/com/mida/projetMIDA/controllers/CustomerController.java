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

import com.mida.projetMIDA.Utils;
import com.mida.projetMIDA.models.Customer;
import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.services.CustomerService;
import com.mida.projetMIDA.services.UserService;


@Controller
@RequestMapping("Clients")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@Autowired
	private UserService uservice;
	
	@GetMapping("/clients")
    public String showCustomers(@RequestParam(name="info",defaultValue = "") String info,ModelMap model) {
		List<Customer> customers = service.getCustomers();
        model.put("customers",customers);
        model.addAttribute("info",info);
        return "clients";
    }
	 @GetMapping("/client-ajout")
	    public String showAddCustomerPage(ModelMap model) {
	        model.addAttribute("customer", new 	Customer());
	        model.addAttribute("type",null);
	        model.addAttribute("users", uservice.getUsers());
	        return "client";
	    }

	    @GetMapping( "/liste-clients/{id}")
	    public String deleteCustomer(@PathVariable(value="id") Long id,RedirectAttributes attr) {
	        service.deleteCustomer(id);
	        attr.addAttribute("info", "supprimer");
	        return "redirect:/Clients/clients";
	    }

	    @GetMapping("/client/{id}")
	    public String showUpdateCustomerPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Customer customer = service.getCustomerById(id).get();
	        model.addAttribute("users", uservice.getUsers());
	        model.put("customer",customer);
	        model.addAttribute("type","edit");
	        return "client";
	    }
	    @PostMapping(value = "/client/{id}")
	    public String updateCustomer( @Valid Customer u, BindingResult result,RedirectAttributes attr,@PathVariable(value="id") Long customer_id) {
	        if (result.hasErrors()) {
	            return "client";
	        }
	        service.updateCustomer(customer_id, u);
	        attr.addAttribute("info", "editer");
	        return "redirect:/Clients/clients";
	    }
	    @PostMapping(value = "/client-ajout")
	    public String addCustomer(@RequestParam String mail,@Valid Customer u, BindingResult result,RedirectAttributes attr) {

	        if (result.hasErrors()) {
	            return "client";
	        }
	        u.setCreatedDate(Utils.setDate());
	        User user= uservice.getUsersByEmail(mail);
	        u.setUserCustomer(user);
	        service.addCustomer(u);
	        attr.addAttribute("info", "creer");
	        return "redirect:/Clients/clients";
	    }
}
