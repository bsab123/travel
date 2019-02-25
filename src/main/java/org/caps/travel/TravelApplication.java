package org.caps.travel;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "org.caps.travel")
@MapperScan(basePackages = "org.caps.travel.mapper")
public class TravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
}

