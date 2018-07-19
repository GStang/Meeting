package com.swpuiot.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.swpuiot"},excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ANNOTATION,value = EnableWebMvc.class
        )
})
@Import(value =WebConfig.class)
@ImportResource("classpath:spring-mybatis.xml")
public class RootConfig {
    //    @Bean
//    public MultipartResolver multipartResolver(){
//        return new StandardServletMultipartResolver();
//    }
    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean(){
        return new ConversionServiceFactoryBean();
    }
}
