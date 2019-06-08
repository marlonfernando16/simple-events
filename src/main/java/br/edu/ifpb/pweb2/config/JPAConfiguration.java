package br.edu.ifpb.pweb2.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//Heroku config
		//Comentar essas linhas quando for usar localmente
		//Descomentá-las quando for dar push para o github
		dataSource.setUsername(System.getenv("DATABASE_USER"));
		dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
		dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
		dataSource.setDriverClassName("org.postgresql.Driver");


		//Local config
		// Descomentar essas linhas quando for usar localmente
		//Comentá-las quando for dar push para o github
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("123");
//		dataSource.setUrl("jdbc:postgresql://localhost/simple-events");
//		dataSource.setDriverClassName("org.postgresql.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		factoryBean.setJpaProperties(props);
		
		factoryBean.setPackagesToScan("br.edu.ifpb.pweb2.model");
		
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager (EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
