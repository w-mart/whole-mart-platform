package com.wholemart.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.wholemart")
@EntityScan("com.wholemart")
@EnableJpaRepositories("com.wholemart")
public class WholeMartPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(WholeMartPlatformApplication.class, args);
    }
}
