package com.accion;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "com.accion.repo.mysql", entityManagerFactoryRef = "mysqlEntityManager", transactionManagerRef = "mysqlTransactionManager")
public class MySQLConfiguration {

	@Autowired
	private Environment env;

	@Bean(name = "mysqlEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean mysqlEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysqlDataSource());
		em.setPackagesToScan(new String[] { "com.accion.model.mysql" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("mysql.hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@Primary
	public DataSource mysqlDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("mysql.datasource.url"));
		dataSource.setUsername(env.getProperty("mysql.datasource.username"));
		dataSource.setPassword(env.getProperty("mysql.datasource.password"));

		return dataSource;
	}

	@Bean(name = "mysqlTransactionManager")
	@Primary
	public PlatformTransactionManager mysqlTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(mysqlEntityManager().getObject());
		return transactionManager;
	}
}
