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

import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/liste-utilisateurs")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", service.getUsers());
        return "utilisateurs";
    }
	@GetMapping("/utilisateurs")
    public String searchUsers(ModelMap model) {
        return null;
    }
	 @GetMapping(value = "/utilisateur-ajout")
	    public String showAddUserPage(ModelMap model) {
	        model.addAttribute("user", new User());
	        model.addAttribute("type",null);
	        return "utilisateur";
	    }

	    @GetMapping(value = "/liste-utilisateurs/{id}")
	    public String deleteUser(@PathVariable(value="id") Long id) {
	        service.deleteUser(id);
	        return "redirect:/liste-utilisateurs";
	    }

	    @GetMapping(value = "/utilisateur/{id}")
	    public String showUpdateUserPage(@PathVariable(value="id") Long id, ModelMap model) {
	        User user = service.getUserById(id).get();
	        model.addAttribute("user",user);
	        model.addAttribute("type","edit");
	        return "utilisateur";
	    }
	    @RequestMapping(value = "/utilisateur/{id}")
	    public String updateVisit(ModelMap model,@PathVariable(value="id") Long user_id, @Valid User u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "utilisateur";
	        }
	        service.updateUser(user_id,u);
	        return "redirect:/liste-utilisateurs";
	    }
	    @PostMapping(value = "/utilisateur-ajout")
	    public String addUser(ModelMap model, @Valid User u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "utilisateur";
	        }
	        service.saveUser(u);
	        return "redirect:/liste-utilisateurs";
	    }
	/*    @GetMapping(value = "/utilisateurs/{num}")
	    public String findPaginated(@PathVariable(value="num") int num,@RequestParam String keyword,ModelMap model) {
	    	int size = 8;
	    	Page<User> page=service.findPaginated(num, size);
	    	List<User> listUsers=null;
	    	if(keyword!=null) {
	    		listUsers=service.findByKyword(keyword);
	    	}else {
	    		listUsers=page.getContent();
	    	}
	    	model.addAttribute("current",num);
	    	model.addAttribute("totalPages",page.getTotalPages());
	    	model.addAttribute("totalItems",page.getTotalElements());
	    	model.addAttribute("users",listUsers);
	        return "utilisateurs";
	    }
	    */
}
