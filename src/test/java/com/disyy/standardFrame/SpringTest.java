package com.disyy.standardFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.disyy"})
public class SpringTest {
	public static void main(final String[] args) {
		SpringApplication.run(SpringTest.class, args);
		}
}
