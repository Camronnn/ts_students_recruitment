//package com.amirdigiev.tsaritsynostudentportfolio.config;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AvailableSettings;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = {"com.amirdigiev.tsaritsynostudentportfolio.repository"})
//@ComponentScan({"com.amirdigiev.tsaritsynostudentportfolio.config"})
//@PropertySource(value = { "classpath:application.properties" })
//public class HibernateConfig {
//
//    private final Environment env;
//
//    @Autowired
//    public HibernateConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
//        return dataSource;
//    }
//
//    private Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put(AvailableSettings.FORMAT_SQL, env.getRequiredProperty("spring.jpa.properties.hibernate.format_sql"));
//        properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
//        properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("spring.jpa.properties.hibernate.show_sql"));
//        properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, env.getRequiredProperty("spring.jpa.properties.hibernate.enable_lazy_load_no_trans"));
//        return properties;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(getDataSource());
//        sessionFactory.setPackagesToScan("com.amirdigiev.tsaritsynostudentportfolio.model");
//        sessionFactory.setHibernateProperties(getHibernateProperties());
//        return sessionFactory;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager txManager = new HibernateTransactionManager();
//        txManager.setSessionFactory(sessionFactory);
//        return txManager;
//    }
//}
