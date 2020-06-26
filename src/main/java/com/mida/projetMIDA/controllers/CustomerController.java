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

import com.mida.projetMIDA.models.Customer;
import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.services.CustomerService;
import com.mida.projetMIDA.services.UserService;


@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@Autowired
	private UserService uservice;
	
	@GetMapping("/")
    public String showCustomers(ModelMap model) {
		List<Customer> customers = service.getCustomers();
        model.put("customers",customers);
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
	    public String deleteCustomer(@PathVariable(value="id") Long id) {
	        service.deleteCustomer(id);
	        return "redirect:/";
	    }

	    @GetMapping("/client/{id}")
	    public String showUpdateCustomerPage(@PathVariable(value="id") Long id, ModelMap model) {
	        Customer customer = service.getCustomerById(id).get();
	        model.addAttribute("users", uservice.getUsers());
	        model.put("customer",customer);
	        model.addAttribute("type","edit");
	        return "client";
	    }
	    @RequestMapping(value = "/client/{id}")
	    public String updateCustomer(ModelMap model,@PathVariable(value="id") Long customer_id, @Valid Customer u, BindingResult result) {
	        if (result.hasErrors()) {
	            return "client";
	        }
	        service.updateCustomer(customer_id, u);
	        return "redirect:/";
	    }
	    @PostMapping(value = "/client-ajout")
	    public String addCustomer(ModelMap model, @Valid Customer u,@RequestParam String mail, BindingResult result) {

	        if (result.hasErrors()) {
	            return "client";
	        }
	        User user= uservice.getUsersByEmail(mail);
	        u.setUserCustomer(user);
	        service.addCustomer(u);
	        return "redirect:/";
	    }
}
