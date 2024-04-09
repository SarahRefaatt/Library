package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.example.demo.entity.User;
@Repository
public interface UserReposetory extends JpaRepository<User, Long> {
	
	
	User findUserByName (String name);
	
	User findUserById (Long id);
	
	
	//List <User> findUserByRolesName (String name);
	
	Boolean existsByName(String name);

}
