package org.caps.travel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@EnableSwagger2
@SpringBootApplication(scanBasePackages = "org.caps.travel")
@MapperScan(basePackages = "org.caps.travel.mapper")
public class TravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
}

