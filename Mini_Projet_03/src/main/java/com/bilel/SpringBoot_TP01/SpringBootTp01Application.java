package com.bilel.SpringBoot_TP01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.bilel.SpringBoot_TP01.entities.Course;
import com.bilel.SpringBoot_TP01.entities.Speciality;
import com.bilel.SpringBoot_TP01.entities.Teacher;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class SpringBootTp01Application implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTp01Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Teacher.class);
		repositoryRestConfiguration.exposeIdsFor(Speciality.class);
		repositoryRestConfiguration.exposeIdsFor(Course.class);
	}

}
