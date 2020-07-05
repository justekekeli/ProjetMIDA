package com.mida.projetMIDA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mida.projetMIDA.models.User;
import com.mida.projetMIDA.repositories.UserRepository;

@Service 
public class UserService {

	@Autowired
	private UserRepository repo;
	
		public List <User> getUsers() {
	        return repo.findAll();
	    }
		public User getUsersByEmail(String email) {
	        return repo.findByEmail(email);
	    }

	    public Optional<User> getUserById(Long id) {
	        return repo.findById(id);
	    }

	    public void updateUser(Long id,User user) {
	    	User u=repo.findById(id).orElse(null);
	    	u.setAddress(user.getAddress());
	    	u.setIsAdmin(user.getIsAdmin());
	    	u.setCreatedDate(user.getCreatedDate());
	    	u.setEmail(user.getEmail());
	    	u.setFirstname(user.getFirstname());
	    	u.setPassword(user.getPassword());
	    	u.setProfile(user.getProfile());
	    	u.setSurname(user.getSurname());
	        repo.save(u);
	    }

	    public void addUser(User u) {
	        repo.save(u);
	    }
	    public void deleteUser(Long id) {
	        Optional <User> u = repo.findById(id);
	        if (u.isPresent()) {
	            repo.delete(u.get());
	        }
	    }
	    public void saveUser(User u) {
	        repo.save(u);
	    }
	 /*   public Page<User> findPaginated(int num,int size){
	    	Pageable pageable = PageRequest.of(num - 1, size);
			return this.repo.findAll(pageable);
	    	
	    }
	    
	    public List <User> findByKyword(String keyword){
	    	return repo.findByKeyword(keyword);
	    }*/
}
