package com.mida.projetMIDA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mida.projetMIDA.models.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
