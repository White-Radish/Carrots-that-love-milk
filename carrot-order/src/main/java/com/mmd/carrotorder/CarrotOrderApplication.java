package com.mmd.carrotorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 整合mybatis plus ,knife4j
 */
@SpringBootApplication
@EnableEurekaClient
public class CarrotOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarrotOrderApplication.class, args);
    }

}
