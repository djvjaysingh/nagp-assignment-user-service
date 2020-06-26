package com.airtel.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;

/**
 * Main SpringBootApplication class for running the application.
 * 
 * @author digvijayadhikari
 */
@SpringBootApplication
@EntityScan(basePackages = { "com.airtel.topic.entity" })
@EnableJpaRepositories(repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class)
public class TopicSpringApplication {

	/**
	 * SpringApplication runner method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TopicSpringApplication.class, args);
	}

}
