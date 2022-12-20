package com.cognologix.assignment.BankSystemAssignment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankSystemAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemAssignmentApplication.class, args);
		System.out.println("Project Started...........");
	}
    @Bean
    public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
