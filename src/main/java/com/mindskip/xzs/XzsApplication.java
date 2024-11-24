package com.mindskip.xzs;

import com.mindskip.xzs.configuration.property.MinioConfig;
import com.mindskip.xzs.configuration.property.SystemConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(value = { SystemConfig.class, MinioConfig.class})
public class XzsApplication {
    public static void main(String[] args) {
        SpringApplication.run(XzsApplication.class, args);
    }
}
