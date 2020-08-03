package com.mida.projetMIDA.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	        User user = userRepository.findByEmail(email);

	        Set <GrantedAuthority> grantedAuthorities=new HashSet<GrantedAuthority>();
	         grantedAuthorities.add(new SimpleGrantedAuthority(user.getIsAdmin()));

	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	    }

}
