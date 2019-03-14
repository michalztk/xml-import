package com.uploader.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 * Configuration class for annotation based configuration. Reads hibernate and
 * database properties from application.properties file
 * 
 * @author michal
 *
 */
@Configuration
@ComponentScan("com.uploader")
@PropertySource("classpath:application.properties")
public class Config {

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Autowired
	private DataSource dataSource;

	/**
	 * Creates Hibernate session factory
	 * 
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.uploader.entity");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	/**
	 * Creates datasource
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}

	/**
	 * Creates transaction manager
	 * 
	 * @param sessionFactory
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	/**
	 * Reads hibernate properties
	 * 
	 * @return
	 */
	Properties hibernateProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.ddl-auto", ddlAuto);
		props.setProperty("hibernate.dialect", hibernateDialect);

		return props;
	}

}
