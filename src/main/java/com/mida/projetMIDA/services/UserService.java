package com.mida.projetMIDA.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.MyUserDetails;
import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.repositories.UserRepository;

@Service 
public class UserService  implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
		public int lenghtList() {
			return this.getUsers().size();
		}
		public List <User> getUsers() {
	        return repo.findAll();
	    }
		public User getUsersByEmail(String email) {
	        return repo.findByEmail(email);
	    }
		
	    public List <User> findBySurname(String name){
	    	return repo.findBySurname(name);
	    }

	    public Optional<User> getUserById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateUser(Long id,User user) {
	    	User u=repo.findById(id).orElse(null);
	    	u.setAddress(user.getAddress());
	    	u.setCreatedDate(user.getCreatedDate());
	    	u.setEmail(user.getEmail());
	    	u.setFirstname(user.getFirstname());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass=encoder.encode(user.getPass());
			u.setPass(pass);
	    	u.setSurname(user.getSurname());
	    	u.setRoles(user.getRoles());
	        repo.save(u);
	        
	    }

	    public void addUser(User u) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass=encoder.encode(u.getPass());
			u.setPass(pass);
	        repo.save(u);
	    }
	    public void deleteUser(Long id) {
	        Optional <User> u = repo.findById(id);
	        if (u.isPresent()) {
	            repo.delete(u.get());
	        }
	    }
	 /*   public Page<User> findPaginated(int num,int size){
	    	Pageable pageable = PageRequest.of(num - 1, size);
			return this.repo.findAll(pageable);
	    	
	    }
	    
	    public List <User> findByKyword(String keyword){
	    	return repo.findByKeyword(keyword);
	    }
	    @Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user= repo.findByEmail(username);
			if(user==null) {
				throw new UsernameNotFoundException("Utilisateur non valide ");
			}
			Set <GrantedAuthority> grantedAuthorities = new HashSet < > ();
	        grantedAuthorities.add(new SimpleGrantedAuthority(user.getIsAdmin()));
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),grantedAuthorities);
		}
	    *
	    */
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = this.getUsersByEmail(username);		
			if(user == null) {
				throw new UsernameNotFoundException("L'utilisateur avec ce mail est introuvable");
			}			
			return new MyUserDetails(user);
		}
		

}
