package by.itacademy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Yury V. on 28.06.17.
 */

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = "by.itacademy.services")
public class ServiceConfig {

}
