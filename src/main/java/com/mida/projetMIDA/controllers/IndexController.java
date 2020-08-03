package com.mida.projetMIDA.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mida.projetMIDA.AgreementState;
import com.mida.projetMIDA.models.Agreement_selling;
import com.mida.projetMIDA.services.Agreement_sellingService;


@Controller
public class IndexController {
	@Autowired
	private Agreement_sellingService service;
	private List<Agreement_selling> sellings=new ArrayList<Agreement_selling>();

	@GetMapping("/")
    public String statistics(ModelMap model) {
		  for(Agreement_selling ag: service.getAgreement()) {
			  if(ag.getState().equals(AgreementState.CONCLU)) {
				  sellings.add(ag);
			  }
		  }
		  ObjectMapper objectMapper = new ObjectMapper();
		  try {
			model.addAttribute("sellings", objectMapper.writeValueAsString(sellings));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "index";
    }
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		return "login";
	}
}
