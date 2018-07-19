package com.swpuiot.initializer;

import com.swpuiot.config.RootConfig;
import com.swpuiot.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.MultipartConfig;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class};

    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        super.customizeRegistration(registration);
//        registration.setMultipartConfig(
//                new MultipartConfigElement("C:\\Users\\guangsheng.tang\\Desktop\\meeting\\uploads")
//        );
//    }
}
