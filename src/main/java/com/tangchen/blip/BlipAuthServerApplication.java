package com.tangchen.blip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author tangchen
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class BlipAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlipAuthServerApplication.class, args);
    }
}
