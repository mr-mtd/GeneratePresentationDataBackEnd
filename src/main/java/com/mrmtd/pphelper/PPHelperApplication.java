package com.mrmtd.pphelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mrmtd.pphelper"})
public class PPHelperApplication {
	public static void main(String[] args) {
		SpringApplication.run(PPHelperApplication.class, args);
	}
}
