package com.wholemart.platform;

import com.wholemart.common.constants.CommonConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = CommonConstants.BASE_PACKAGE)
@EntityScan(CommonConstants.BASE_PACKAGE)
@EnableJpaRepositories(CommonConstants.BASE_PACKAGE)
public class WholeMartPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(WholeMartPlatformApplication.class, args);
    }
}
