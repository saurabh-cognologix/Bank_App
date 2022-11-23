package com.cognologix.assignment.BankSystemAssignment;

import com.cognologix.assignment.BankSystemAssignment.demoTest.MyFirstJUnitJupiterTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class BankSystemAssignmentApplicationTests {

    MyFirstJUnitJupiterTests m = new MyFirstJUnitJupiterTests();
	@Test // denotes that a method is test method
	void contextLoads() {
	}
	@Test
	void testSum(){
		//expected output
		int expectedResult = 17;

        // actual output
		int result = m.doSum(12,3,2);
		assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	void testMult(){

		//expected output
		int expectedResult = 25;

		//actual output
		int multResult = m.doMul(5,5);
		System.out.println(multResult);

		assertThat(multResult).isEqualTo(expectedResult);
	}

}
