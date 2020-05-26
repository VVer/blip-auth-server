package com.tangchen.blip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tangchen
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.security.foxtc", "com.tangchen.blip"})
public class BlipAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlipAuthServerApplication.class, args);
    }
}
