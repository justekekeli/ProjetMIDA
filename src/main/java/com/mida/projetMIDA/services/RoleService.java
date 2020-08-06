package com.mida.projetMIDA.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.Role;
import com.mida.projetMIDA.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repo;
	
	public Role find(String name) {
		return repo.findByName(name);
	}
}
