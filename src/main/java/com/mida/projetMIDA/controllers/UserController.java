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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mida.projetMIDA.Utils;
import com.mida.projetMIDA.models.Role;
import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.services.RoleService;
import com.mida.projetMIDA.services.UserService;

@Controller
@RequestMapping("Utilisateurs")
public class UserController {
	@Autowired
	private UserService service;
	
	
	@Autowired
	private RoleService serviceRs;
	
	@GetMapping("/liste-utilisateurs")
    public String showUsers(@RequestParam(name="info",defaultValue = "") String info,ModelMap model) {
        model.addAttribute("users", service.getUsers());
        model.addAttribute("info",info);
        return "utilisateurs";
    }
	@GetMapping(value = "/utilisateur-ajout")
    public String showAddUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("type",null);
        return "utilisateur";
    }

	    @GetMapping(value = "/liste-utilisateurs/{id}")
	    public String deleteUser(@PathVariable(value="id") Long id,RedirectAttributes attr) {
	        service.deleteUser(id);
	        attr.addAttribute("info", "supprimer");
	        return "redirect:/Utilisateurs/liste-utilisateurs";
	    }

	    @GetMapping(value = "/utilisateur/{id}")
	    public String showUpdateUserPage(@PathVariable(value="id") Long id, ModelMap model) {
	        User user = service.getUserById(id).get();
	        model.addAttribute("user",user);
	        model.addAttribute("type","edit");
	        return "utilisateur";
	    }
	    @RequestMapping(value = "/utilisateur/{id}")
	    public String updateVisit(RedirectAttributes attr,@PathVariable(value="id") Long user_id, @Valid User u, BindingResult result) {

	        if (result.hasErrors()) {
	            return "utilisateur";
	        }
	        service.updateUser(user_id,u);
	        attr.addAttribute("info", "editer");
	        return "redirect:/Utilisateurs/liste-utilisateurs";
	    }
	    @PostMapping(value = "/utilisateur-ajout")
	    public String addUser(RedirectAttributes attr, @Valid User u,@RequestParam String statut, BindingResult result) {

	        if (result.hasErrors()) {
	            return "utilisateur";
	        }
	        u.setCreatedDate(Utils.setDate());
	        Role r= serviceRs.find(statut);
	        u.getRoles().add(r);
	        service.addUser(u);
	        attr.addAttribute("info", "creer");
	        return "redirect:/Utilisateurs/liste-utilisateurs";
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
