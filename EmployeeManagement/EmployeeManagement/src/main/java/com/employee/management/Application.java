package com.employee.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Application {

	//logging
	static final Logger logger  = LogManager.getLogger(Application.class.getName());
	
	public static void main(String[] args) {
		logger.info("Application started");
		SpringApplication.run(Application.class, args);

	}

}
/*{
    "firstName":"Dish",
    "lastName":"K",
    "department":{
        "department_id":"2",.\
        "department_Name":"CSE"
    }
    
}*/
