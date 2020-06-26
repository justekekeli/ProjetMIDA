package com.mida.projetMIDA.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mida.projetMIDA.models.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	List <User> findBySurname(String name);
	
	/*@Query(value="select * from User u where e.surname like %:keyword% or e.firstname like %:keyword%",nativeQuery=true)
	List <User> findByKeyword(@Param("keyword")String keyword);*/
}
