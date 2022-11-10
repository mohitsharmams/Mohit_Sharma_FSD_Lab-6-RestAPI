package com.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.entity.Role;
import com.entity.Student;
import com.entity.User;
import com.repository.StudentRepository;
import com.repository.UserRepository;



@Component
public class BootstrapAppData {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder1() {
		return new BCryptPasswordEncoder();
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void insertStudentData(ApplicationReadyEvent event) {
		
		// Creating Dummy data in database
		Student student = new Student();
		student.setFirstName("Piyush");
		student.setLastName("Saxena");
		student.setCourse("Taxation");
		student.setCountry("India");
		
		Student student1 = new Student();
		student1.setFirstName("Laxman");
		student1.setLastName("Singh");
		student1.setCourse("Commerce");
		student1.setCountry("India");
		
		
		this.studentRepository.save(student);
		this.studentRepository.save(student1);
		
	}

	
	@EventListener(ApplicationReadyEvent.class)
	public void insertRolesData(ApplicationReadyEvent event) {
		
		// Creating application ready users in database
		User ananya = new User();
		ananya.setUsername("ananya");
		ananya.setPassword(passwordEncoder1().encode("ananya"));
		
		User hitarth = new User();
		hitarth.setUsername("hitarth");
		hitarth.setPassword(passwordEncoder1().encode("hitarth"));
		
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");		

		ananya.addRole(adminRole);
		hitarth.addRole(userRole);
		
		this.userRepository.save(ananya);
		this.userRepository.save(hitarth);
		
	}


}
