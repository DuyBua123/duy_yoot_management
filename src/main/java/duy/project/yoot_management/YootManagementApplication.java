package duy.project.yoot_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class YootManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(YootManagementApplication.class, args);
	}

}
