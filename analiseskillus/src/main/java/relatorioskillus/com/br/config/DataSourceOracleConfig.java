package relatorioskillus.com.br.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import relatorioskillus.com.br.model.Relatorio;
import relatorioskillus.com.br.repositorio.Relatorios;

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackageClasses=Relatorios.class , 
						   entityManagerFactoryRef="oracleDBEmFactory" , 
						   transactionManagerRef = "oracleDSTransactionManager")
public class DataSourceOracleConfig {
	 
	  
	 @Bean
	 @ConfigurationProperties("spring.datasourcebanco2")
	 public DataSourceProperties oracleDSProperties() {
	        return new DataSourceProperties();
	                
	 }
	 
	  @Bean
	  public DataSource oracleDataSource(@Qualifier("oracleDSProperties") DataSourceProperties oracleDSProperties) {
	          DataSource dataSource = oracleDSProperties.initializeDataSourceBuilder().build();
	          return dataSource;
	  }
	       
	   @Bean
	   public LocalContainerEntityManagerFactoryBean oracleDBEmFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource, EntityManagerFactoryBuilder builder) {
	          LocalContainerEntityManagerFactoryBean factory = builder.dataSource(oracleDataSource).packages(Relatorio.class).build();
	          factory.setPersistenceUnitName("oracle");
	          return factory; 
	   }
	   
	    
	   @Bean
	   public PlatformTransactionManager oracleDSTransactionManager(EntityManagerFactory factory) {
	         return new JpaTransactionManager(factory);
	   }
	    
	     
}