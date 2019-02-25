package org.caps.travel.commons;

import org.caps.travel.interceptor.ConstantsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author caps
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public ConstantsInterceptor constantsInterceptor() {
        return new ConstantsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 常量拦截器
        registry.addInterceptor(constantsInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}