package org.ono.fscl.process.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"fscl.shadow", "fscl.process.service"})
public class FsclProcessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsclProcessServiceApplication.class, args);
	}

}
