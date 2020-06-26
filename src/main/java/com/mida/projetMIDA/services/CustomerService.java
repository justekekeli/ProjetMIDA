package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Customer;
import com.mida.projetMIDA.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
		public List <Customer> getCustomers() {
	        return repo.findAll();
	    }
		public Customer getCustomersByCin(String cin) {
	        return repo.findByCin(cin);
	    }

	    public Optional<Customer> getCustomerById(Long id) {
	        return repo.findById(id);
	    }

	    public void  updateCustomer(Long id,Customer c) {
	    	Customer r =repo.findById(id).orElse(null);
	    	r.setAddressCustomer(c.getAddressCustomer());
	    	r.setCin(c.getCin());
	    	r.setFirstname1(c.getFirstname1());
	    	r.setFirstname2(c.getFirstname2());
	    	r.setSurname(c.getSurname());
	    	r.setTel(c.getTel());
	    	r.setWork(c.getWork());
	        repo.save(r);
	    }

	    public void addCustomer(Customer c) {
	        repo.save(c);
	    }
	    public void deleteCustomer(Long id) {
	        Optional <Customer> c = repo.findById(id);
	        if (c.isPresent()) {
	            repo.delete(c.get());
	        }
	    }


}
