package com.accion;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "com.accion.repo.postgres", entityManagerFactoryRef = "postGresEntityManager", transactionManagerRef = "postGresTransactionManager")
public class PostGresConfiguration {

	@Autowired
	private Environment env;

	@Bean(name = "postGresEntityManager")
	public LocalContainerEntityManagerFactoryBean postGresEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(postGresDataSource());
		em.setPackagesToScan(new String[] { "com.accion.model.postgres" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("postgres.hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public DataSource postGresDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("postgres.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("postgres.datasource.url"));
		dataSource.setUsername(env.getProperty("postgres.datasource.username"));
		dataSource.setPassword(env.getProperty("postgres.datasource.password"));

		return dataSource;
	}

	@Bean(name = "postGresTransactionManager")
	public PlatformTransactionManager postGresTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(postGresEntityManager().getObject());
		return transactionManager;
	}
}
