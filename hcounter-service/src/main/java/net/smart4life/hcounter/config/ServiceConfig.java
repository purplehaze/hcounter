package net.smart4life.hcounter.config;

import org.springframework.context.annotation.*;

import net.smart4life.hcounter.service.ServicePackagePlaceholder;


/**
 * Created by roman on 30.12.2014.
 */
@Configuration
@ComponentScan(basePackageClasses = {ServiceConfig.class, ServicePackagePlaceholder.class})
public class ServiceConfig {

//    @Bean
//    public CommonAnnotationBeanPostProcessor beanPostProcessor(){
//        return new CommonAnnotationBeanPostProcessor();
//    }
}
