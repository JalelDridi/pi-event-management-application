package tn.esprit.adminmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(tn.esprit.adminmicroservice.AdminMicroserviceApplication.class, args);
	}

}
