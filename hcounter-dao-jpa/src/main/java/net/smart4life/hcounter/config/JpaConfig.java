package net.smart4life.hcounter.config;

import net.smart4life.hcounter.daojpa.BaseDAOImpl;
import net.smart4life.hcounter.datamodel.entity.BaseEntity;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by roman on 30.12.2014.
 */
@Configuration
@ComponentScan(basePackageClasses = {BaseDAOImpl.class, JpaConfig.class})
//@Import({ValidationConfig.class, WebConfig.class})
@PropertySource("classpath:spring/${APP_ENV:dev}-data-access.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));

        return ds;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.valueOf(env.getProperty("jpa.databaseDialect")));
        hibernateJpaVendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("jpa.showSql")));
//        hibernateJpaVendorAdapter.setGenerateDdl(Boolean.valueOf(env.getProperty("jpa.generateDdl")));

        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter){
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource(dataSource);
        emFactory.setPackagesToScan(BaseEntity.class.getPackage().getName());
        emFactory.setJpaVendorAdapter(jpaVendorAdapter);
//        emFactory.setPersistenceUnitName("hcounter");

        final Map<String, Object> jpaPropertyMap = new HashMap<String, Object>();
//        jpaPropertyMap.put(AvailableSettings.STATEMENT_FETCH_SIZE, env.getProperty("jpa.fetchSize"));
//        jpaPropertyMap.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getProperty("jpa.batchSize"));
//        jpaPropertyMap.put(AvailableSettings.JMX_ENABLED, Boolean.valueOf(env.getProperty("jpa.jmxEnabled")));
//        jpaPropertyMap.put(AvailableSettings.GENERATE_STATISTICS, Boolean.valueOf(env.getProperty("jpa.generateStatistics")));
        jpaPropertyMap.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        emFactory.setJpaPropertyMap(jpaPropertyMap);

        return emFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager tm = new JpaTransactionManager(entityManagerFactory);
        tm.setNestedTransactionAllowed(true);

        return tm;
    }


}
