
package com.example.proxmoxautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProxmoxautomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxmoxautomationApplication.class, args);
	}
}