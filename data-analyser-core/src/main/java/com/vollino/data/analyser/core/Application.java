package com.vollino.data.analyser.core;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Bruno Vollino
 */
@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplication(Application.class).run(args);
        CamelSpringBootApplicationController applicationController =
                applicationContext.getBean(CamelSpringBootApplicationController.class);
        applicationController.run();
    }
}
